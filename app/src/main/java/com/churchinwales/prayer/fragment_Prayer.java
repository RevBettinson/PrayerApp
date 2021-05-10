package com.churchinwales.prayer;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_Prayer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_Prayer extends Fragment {

    TextView tv_Prayer;
    TextView tv_Title;
    Button btn_Language;
    //Note: this should use Androids built-in language stuffs
    String language="EN";
    JSONArray lectionaryJSON;
    String myData="";
    String prayerType = "";
    Helper myHelper;

    private ProgressBar spinner;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_Prayer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_Prayer.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_Prayer newInstance(String param1) {
        fragment_Prayer fragment = new fragment_Prayer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            /**
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
**/
            prayerType = getArguments().getString("Type");
            Log.v("TAG",prayerType);
        }
        else {
            prayerType = "MorningPrayer";
        }


        myHelper =new Helper();
        myData ="";
    }

   // @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_prayer, container, false);


        tv_Prayer = (TextView)rootView.findViewById(R.id.txt_MainView);
        tv_Prayer.setMovementMethod(new ScrollingMovementMethod());
        tv_Title = (TextView)rootView.findViewById(R.id.txt_title);

        btn_Language = (Button)rootView.findViewById(R.id.btn_Language);

        btn_Language.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fragment_Prayer.this.onClickBtnLanguage();
            }
        });

        spinner = (ProgressBar) rootView.findViewById(R.id.ProgressBar2);
        //This needs to be a translatable string. TODO
        if(prayerType.equalsIgnoreCase("MorningPrayer")) {
            tv_Title.setText(getString(R.string.app_MorningPrayer));
        }

        if(prayerType.equalsIgnoreCase("EveningPrayer")) {
            tv_Title.setText(getString(R.string.app_EveningPrayer));
        }

        spinner.setVisibility(View.VISIBLE);

        this.setUpPrayer(prayerType);

        spinner.setVisibility(View.GONE);

        return rootView;

    }

    protected void setUpPrayer(String prayerType)
    {
        Context app_Context = getActivity().getApplicationContext();
        //Context app_Context = getApplicationContext();


        SpannableStringBuilder myDocument = new SpannableStringBuilder("");


        try {
                if(myData == "") {

                    myData = readFile(app_Context, "/Prayer/Layout/"+prayerType+".json");

                    String path = app_Context.getFilesDir().getPath() + "/Prayer/Layout/";
                    Log.d("Files", "Path: " + path);
                    File directory = new File(path);
                    File[] files = directory.listFiles(new FilenameFilter() {
                        public boolean accept(File dir, String name) {
                            return name.toLowerCase().endsWith(".json")&&(name.toLowerCase().startsWith(prayerType.toLowerCase()));
                        }
                    });
                    Log.d("Files", "Size: " + files.length);
                    for (int i = 0; i < files.length; i++) {
                        Log.d("Files", "FileName:" + files[i].getName());
                    }

                    int min = 0;
                    int max = files.length - 1;
                    int random = (int) Math.floor(Math.random() * (max - min + 1) + min);

                    myData = readFile(app_Context, "/Prayer/Layout/" + files[random].getName());
                }

        } catch (IOException e) {
                e.printStackTrace();
                myDocument.append("FATAL: can't find layout file<br>");
        }

        StringBuilder data = new StringBuilder();
        try {
            JSONObject jsonRootObject = new JSONObject(myData);
            JSONObject jsonObject = jsonRootObject.optJSONObject(prayerType);



            Iterator keys = jsonObject.keys();


            while(keys.hasNext()) {
                Object key = keys.next();

                Log.v("TAG",((String)key));

                //Will still pick up the key "morning Prayer"!
                if(((String)key).equals("Name")){
                    JSONObject data_JSOB = jsonObject.getJSONObject((String) key);
                    String name = data_JSOB.getString(language);
                    tv_Title.setText(name);
                    //name=name+"<br><br>";
                    //myDocument.append(Html.fromHtml(name));
                }
                if(((String)key).startsWith("Section")){
                    JSONObject data_JSOB = jsonObject.getJSONObject((String) key);

                    /*
                     * If the Files section is an array, then pick a random one
                     * This will eventually also check for preferences
                     */
                    Object isArray = data_JSOB.get("File");
                    if(isArray instanceof JSONArray) {
                        Log.v("TAG", "File is an array");
                        JSONArray myFiles = new JSONArray(data_JSOB.getString("File"));
                        int min = 0;
                        int max = myFiles.length()-1;
                        int random = (int) Math.floor(Math.random()*(max-min+1)+min);
                        data_JSOB.put("File",myFiles.get(random));

                    }
                    SpannableStringBuilder section= getSection(app_Context, data_JSOB);
                    myDocument.append(section);
                }
                if(((String)key).startsWith("Bible")) {
                    JSONObject data_JSOB = jsonObject.getJSONObject((String) key);
                    SpannableStringBuilder bible= getBibleReading(app_Context,data_JSOB.getString("Type"));
                    myDocument.append(bible);
                }



            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        //myDocument.append(confessional());

        tv_Prayer.setText(myDocument, TextView.BufferType.NORMAL);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private SpannableStringBuilder getBibleReading(Context app_context, String type) {

        JSONObject prayer = myHelper.getLectionaryJson(app_context,"MP");
        SpannableStringBuilder text_Prayer = new SpannableStringBuilder();



        try {
            if (type.equalsIgnoreCase("OT")) {
                text_Prayer.append(Html.fromHtml("<br><h2>"+getString(R.string.OTReading)+"</h2>"));
                text_Prayer.append(Html.fromHtml("<a href=https://www.biblegateway.com/passage/?search="+prayer.getString("OT")+">"+prayer.getString("OT")+"</a>"));
                text_Prayer.append(Html.fromHtml("<br><br> "));
            }
            if (type.equalsIgnoreCase("NT")) {
                text_Prayer.append(Html.fromHtml("<br><h2>"+getString(R.string.NewTestamentReading)+"</h2>"));
                text_Prayer.append(prayer.getString("NT"));
                text_Prayer.append(Html.fromHtml("<br><br> "));
            }
            if (type.equalsIgnoreCase("Psalm")) {
                text_Prayer.append(Html.fromHtml("<br><h2>Psalm: </h2>"));
                text_Prayer.append(prayer.getString("Psalm"));
                text_Prayer.append(Html.fromHtml("<br><br> "));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return text_Prayer;

    }

    private SpannableStringBuilder getBibleReadingOld(Context app_context, String type) {

        SpannableStringBuilder reading = new SpannableStringBuilder();
        String myData = "";

        try {
            if(lectionaryJSON == null) {
                InputStream fis = app_context.getAssets().open("dol-year-1.json");

                DataInputStream in = new DataInputStream(fis);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                String strLine;
                while ((strLine = br.readLine()) != null) {


                    myData = myData + strLine;
                }

                br.close();
                in.close();
                fis.close();

                lectionaryJSON = new JSONArray(myData);
            }

            //https://www.biblegateway.com/passage/?search=Deut+9%3A23+-10%3A5&version=NRSV
            //work out the week of the year, starting from the first Sunday of Advent!
            Calendar cal = Calendar.getInstance();

            int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);


            //requires calculations for ADVENT
            dayOfYear = dayOfYear;

            Log.v("TAG", "Day Of Year:" + dayOfYear);


            JSONObject myReadings = (JSONObject) lectionaryJSON.get(dayOfYear);

            //reading = myReadings.getString("year");
            reading.append(Html.fromHtml("<BR>"));
            if (type.equalsIgnoreCase("OT")) {
                //should be a string resource!

                reading.append(Html.fromHtml("<H2>"+getString(R.string.OTReading)+":</H2><br>"));
                String newReading = ((JSONObject) myReadings.getJSONObject("lessons")).getString("first");
                SpannableString string = new SpannableString(newReading);
                string.setSpan(new URLSpan("https://www.biblegateway.com/passage/?search="+newReading+"&version=NRSV"), 0, newReading.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                reading.append(string);

            }

            if (type.equalsIgnoreCase("NT")) {
                //should be a string resource!
                reading.append(Html.fromHtml("<h2>"+getString(R.string.NewTestamentReading)+":</H2><br>"));
                reading.append(((JSONObject) myReadings.getJSONObject("lessons")).getString("second"));
            }

            if (type.equalsIgnoreCase("GP")) {
                //should be a string resource!
                reading.append(Html.fromHtml(getString(R.string.GospelReading) + ":<br>"));
                reading.append(((JSONObject) myReadings.getJSONObject("lessons")).getString("gospel"));
            }

            reading.append(Html.fromHtml("<BR><BR>"));


        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(JSONException e) {
            e.printStackTrace();
        }

        //return new SpannableStringBuilder(Html.fromHtml(reading));
        return reading;
    }

    protected SpannableStringBuilder getSection(Context app_Context, JSONObject data_JSOB) throws JSONException
    {
        String data="";
        try {

            String location = "/Prayer/"+data_JSOB.getString("Location")+"/"+language+"_"+data_JSOB.getString("File")+".txt";
            Log.v("TAG","Looking for:"+location);
            data= readFile(app_Context,location);


        }   catch (IOException e) {
            e.printStackTrace();
            //should be a proper string resource!
            data = getString(R.string.Error)+" :"+data_JSOB.getString("Name")+" "+getString(R.string.FileNotFound)+"!<br><br>";
        }

        SpannableStringBuilder intro = new SpannableStringBuilder(Html.fromHtml(data));
        return intro;
    }

    protected String readFile(Context app_Context, String relativePath) throws IOException
    {
        String myData = "";


        //InputStream fis = app_Context.getDataDir().open("Prayer/MorningPrayer/Confessional/EN_BasicConfessional.txt");

        String fileName = app_Context.getFilesDir().getPath()+relativePath;

        File file = new File(fileName);
        //InputStream fis = app_Context.getDataDir().open("Prayer/MorningPrayer/Confessional/EN_BasicConfessional.txt");

        FileInputStream fis = new FileInputStream(file);
        //FileInputStream fis = openFileInput(fileName);
        DataInputStream in = new DataInputStream(fis);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String strLine;
        while((strLine = br.readLine())!= null) {
            myData = myData + strLine;
        }
//
        br.close();
        in.close();
        fis.close();



        return myData;
    }

    protected void setAppLocale(String localeCode)
    {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(config,dm);

    }


    public void onClickBtnLanguage() {

        if(language.equals("EN")){
            language = "CY";
            this.setAppLocale("cy");
            this.setUpPrayer(prayerType);

        }
        else {
            language="EN";
            this.setAppLocale("EN");
            this.setUpPrayer(prayerType);
        }

        //This is necessary as the app doesn't update 'top level' resources!
        btn_Language.setText(getString(R.string.btn_Language));
    }
}