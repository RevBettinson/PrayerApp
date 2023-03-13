package com.churchinwales.prayer;

import android.Manifest;
import android.content.Context;
<<<<<<< HEAD
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;

import android.os.Handler;
import android.text.Html;

import android.text.SpannableStringBuilder;

import android.text.method.ScrollingMovementMethod;

=======
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;

import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.URLSpan;
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
<<<<<<< HEAD

import android.widget.TextView;
import android.widget.Toast;

import org.crosswire.common.util.CWProject;
import org.crosswire.jsword.book.Book;
=======
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
<<<<<<< HEAD

import java.io.InputStreamReader;

import java.util.Iterator;

import java.util.Locale;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static androidx.preference.PreferenceManager.*;


=======
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_Prayer#newInstance} factory method to
 * create an instance of this fragment.
 */
<<<<<<< HEAD
public class fragment_Prayer extends Fragment implements app_BiblePericope_Callback<String>, Observer,setJswordBible<Book>, setJswordVerse<String>,
SharedPreferences.OnSharedPreferenceChangeListener {
=======
public class fragment_Prayer extends Fragment implements app_BiblePericope_Callback<String>, Observer {
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

    TextView tv_Prayer;
    TextView tv_Title;
    Button btn_Language;
    //Note: this should use Androids built-in language stuffs
<<<<<<< HEAD
    String language = "EN";
    String myData = "";
    String prayerType = "";
    Helper myHelper;
    Handler myHandler;
    BibleReadingsViewModel br_ViewModel = new BibleReadingsViewModel();
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private static final int REQUEST_CODE_ASK_PERMISSONS = 1;
    protected Book bible;
    protected Boolean bibleSet = Boolean.FALSE;
    protected Boolean bibleFound = Boolean.FALSE;
    SharedPreferences sharedPrefs;


=======
    String language="EN";
    JSONArray lectionaryJSON;
    String myData="";
    String prayerType = "";
    Helper myHelper;
    BibleReadingsViewModel br_ViewModel= new BibleReadingsViewModel();
    private ExecutorService executorService = Executors.newFixedThreadPool(2);
    private static final int REQUEST_CODE_ASK_PERMISSONS =1;


    private ProgressBar spinner;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

    public fragment_Prayer() {
        // Required empty public constructor
    }

<<<<<<< HEAD
    public static fragment_Prayer newInstance() {
        fragment_Prayer fragment = new fragment_Prayer();


=======
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
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD

        sharedPrefs = getDefaultSharedPreferences(getContext());
        sharedPrefs.registerOnSharedPreferenceChangeListener(this);

        if (getArguments() != null) {

            prayerType = getArguments().getString("Type");
            AppDebug.log("TAG", prayerType);
        } else {
            prayerType = "MorningPrayer";
        }

        myHelper = new Helper();
        myData = "";
        myHandler = new Handler();


        CWProject.setHome(getContext().getFilesDir().getPath(), getContext().getFilesDir().getPath() + "/JSWORD", ".Jsword");
        HttpReqTask myTask = new HttpReqTask(executorService);
        myTask.getBibleBook(getString(R.string.app_WelshBibleJswordName), this);

    }

    // @Override
=======
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
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_prayer, container, false);


<<<<<<< HEAD
        tv_Prayer = (TextView) rootView.findViewById(R.id.txt_MainView);
        tv_Prayer.setMovementMethod(new ScrollingMovementMethod());
        tv_Title = (TextView) rootView.findViewById(R.id.txt_title);

        btn_Language = (Button) rootView.findViewById(R.id.btn_Language);

        btn_Language.setOnClickListener(new View.OnClickListener() {
=======
        tv_Prayer = (TextView)rootView.findViewById(R.id.txt_MainView);
        tv_Prayer.setMovementMethod(new ScrollingMovementMethod());
        tv_Title = (TextView)rootView.findViewById(R.id.txt_title);

        btn_Language = (Button)rootView.findViewById(R.id.btn_Language);

        btn_Language.setOnClickListener(new View.OnClickListener(){
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
            @Override
            public void onClick(View view) {
                fragment_Prayer.this.onClickBtnLanguage();
            }
        });

        //spinner = (ProgressBar) rootView.findViewById(R.id.ProgressBar2);
        //This needs to be a translatable string. TODO
<<<<<<< HEAD
        if (prayerType.equalsIgnoreCase("MorningPrayer")) {
            tv_Title.setText(getString(R.string.app_MorningPrayer));
            getActivity().setTitle(getString(R.string.app_MorningPrayer));

        }

        if (prayerType.equalsIgnoreCase("EveningPrayer")) {
            tv_Title.setText(getString(R.string.app_EveningPrayer));
            getActivity().setTitle(getString(R.string.app_EveningPrayer));

        }


        this.setDisplayFont();

        this.setUpPrayer(prayerType);

=======
        if(prayerType.equalsIgnoreCase("MorningPrayer")) {
            tv_Title.setText(getString(R.string.app_MorningPrayer));
        }

        if(prayerType.equalsIgnoreCase("EveningPrayer")) {
            tv_Title.setText(getString(R.string.app_EveningPrayer));
        }

       // spinner.setVisibility(View.VISIBLE);

        this.setUpPrayer(prayerType);

        //spinner.setVisibility(View.GONE);

>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        return rootView;

    }

<<<<<<< HEAD
    protected void setUpPrayer(String prayerType) {
=======
    protected void setUpPrayer(String prayerType)
    {
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        Context app_Context = getActivity().getApplicationContext();
        //Context app_Context = getApplicationContext();


        SpannableStringBuilder myDocument = new SpannableStringBuilder("");


<<<<<<< HEAD
        try {
            if (myData.equals("")) {

                myData = readFile(app_Context, "/Prayer/Layout/" + prayerType + ".json");

                String path = app_Context.getFilesDir().getPath() + "/Prayer/Layout/";
                AppDebug.log("Files", "Path: " + path);
                File directory = new File(path);
                File[] files = directory.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.toLowerCase().endsWith(".json") && (name.toLowerCase().startsWith(prayerType.toLowerCase()));
                    }
                });
                AppDebug.log("Files", "Size: " + files.length);
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

=======

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
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        try {
            JSONObject jsonRootObject = new JSONObject(myData);

            br_ViewModel.getObservable().observe(getViewLifecycleOwner(), this);
            JSONObject jsonObject = jsonRootObject.optJSONObject(prayerType);

<<<<<<< HEAD
            Iterator keys = jsonObject.keys();

            while (keys.hasNext()) {
                Object key = keys.next();

                AppDebug.log("TAG", ((String) key));

                //Will still pick up the key "morning Prayer"!
                if (((String) key).equals("Name")) {
                    JSONObject data_JSOB = jsonObject.getJSONObject((String) key);
                    String name = data_JSOB.getString(language);
                    tv_Title.setText(name);

                }
                if (((String) key).startsWith("Section")) {
=======


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
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
                    JSONObject data_JSOB = jsonObject.getJSONObject((String) key);

                    /*
                     * If the Files section is an array, then pick a random one
                     * This will eventually also check for preferences
                     */
                    Object isArray = data_JSOB.get("File");
<<<<<<< HEAD
                    if (isArray instanceof JSONArray) {
                        AppDebug.log("TAG", "File is an array");
                        JSONArray myFiles = new JSONArray(data_JSOB.getString("File"));
                        int min = 0;
                        int max = myFiles.length() - 1;
                        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);
                        data_JSOB.put("File", myFiles.get(random));

                    }

                    br_ViewModel.setValue((String) key, getSection(app_Context, data_JSOB).toString());

                }
                if (((String) key).startsWith("Bible")) {
                    JSONObject data_JSOB = jsonObject.getJSONObject((String) key);
                    getBibleReading(app_Context, data_JSOB.getString("Type").toString(), (String) key);

                }


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

=======
                    if(isArray instanceof JSONArray) {
                        Log.v("TAG", "File is an array");
                        JSONArray myFiles = new JSONArray(data_JSOB.getString("File"));
                        int min = 0;
                        int max = myFiles.length()-1;
                        int random = (int) Math.floor(Math.random()*(max-min+1)+min);
                        data_JSOB.put("File",myFiles.get(random));

                    }

                    br_ViewModel.setValue((String)key,getSection(app_Context,data_JSOB).toString());
                    /**
                    SpannableStringBuilder section= getSection(app_Context, data_JSOB);
                    myDocument.append(section);
                     **/
                }
                if(((String)key).startsWith("Bible")) {
                    JSONObject data_JSOB = jsonObject.getJSONObject((String) key);
                    getBibleReading(app_Context,data_JSOB.getString("Type").toString(),(String)key);
                    /**
                    SpannableStringBuilder bible= getBibleReading(app_Context,data_JSOB.getString("Type"));
                    myDocument.append(bible);
                     **/
                }



            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        //myDocument.append(confessional());
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

        tv_Prayer.setText(myDocument, TextView.BufferType.NORMAL);
    }

    private void getBibleReading(Context app_context, String type, String section) {

<<<<<<< HEAD
        Helper myHelper = new Helper();
        JSONObject prayer = myHelper.getLectionaryJson(app_context, this.prayerType);

        HttpReqTask myTask = new HttpReqTask(executorService);

        try {
            if (type.equalsIgnoreCase("OT")) {
                br_ViewModel.setValue(type + "_Name", "<br><br><h1>" + getString(R.string.OTReading) + "</h1><br>");
            }
            if (type.equalsIgnoreCase("NT")) {
                br_ViewModel.setValue(type + "_Name", "<br><br><h1>" + getString(R.string.NewTestamentReading) + "</h1><br>");
            }
            if (type.equalsIgnoreCase("Psalm")) {
                br_ViewModel.setValue(type + "_Name", "<h1>" + getString(R.string.Psalm) + "</h1><br>");
            }

            String bibleVerse =  myHelper.checkBibleReading(prayer.getString(type));

            br_ViewModel.setValue(type + "_Title", "<h2>" + bibleVerse + "</h2>");
            br_ViewModel.setValue(section, ".." + getString(R.string.app_loading));
            if (checkPermissions()) {
                if (language.equals("CY") & this.bible != null) {
                    myTask.getJswordVerse(this.bible, bibleVerse, section, this);
                } else {
                    myTask.makeBibleRequest(bibleVerse, section, this);
                }
            } else {
                br_ViewModel.setValue(section, "... No internet permission granted");
            }
            br_ViewModel.setValue(type + "_Spacing", "<BR><BR>");
        } catch (Exception e) {
=======
        JSONObject prayer = myHelper.getLectionaryJson(app_context, type);
        SpannableStringBuilder text_Prayer = new SpannableStringBuilder();

        Helper myHelper = new Helper();
        HttpReqTask myTask = new HttpReqTask(executorService);

        try {
            if(type.equalsIgnoreCase("OT")) {
               br_ViewModel.setValue(type+"_Name","<br><br><h1>"+getString(R.string.OTReading)+"</h1><br>");
            }
            if(type.equalsIgnoreCase("NT")) {
                br_ViewModel.setValue(type+"_Name","<br><br><h1>"+getString(R.string.NewTestamentReading)+"</h1><br>");
            }
            if(type.equalsIgnoreCase("Psalm")) {
                br_ViewModel.setValue(type+"_Name","<h1>"+getString(R.string.Psalm)+"</h1><br>");
            }

            br_ViewModel.setValue(type+"_Title","<h2>"+prayer.getString(type)+"</h2>");
            br_ViewModel.setValue(section,".."+getString(R.string.app_loading));
            if(checkPermissions()) {
                myTask.makeBibleRequest(prayer.getString(type), section, this);
            }
            else {
                br_ViewModel.setValue(section,"... No internet permission granted");
            }
            br_ViewModel.setValue(type+"_Spacing","<BR><BR>");
        }
        catch(Exception e) {
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
            e.printStackTrace();

        }
    }


<<<<<<< HEAD
    protected String getSection(Context app_Context, JSONObject data_JSOB) throws JSONException {
        String data = "";
        try {

            String location = "/Prayer/" + data_JSOB.getString("Location") + "/" + language + "_" + data_JSOB.getString("File") + ".txt";
            AppDebug.log("TAG", "Looking for:" + location);
            data = readFile(app_Context, location);


        } catch (IOException e) {
            e.printStackTrace();
            //should be a proper string resource!
            data = getString(R.string.Error) + " :" + data_JSOB.getString("Name") + " " + getString(R.string.FileNotFound) + "!<br><br>";
=======
    @RequiresApi(api = Build.VERSION_CODES.O)
    private SpannableStringBuilder getBibleReadingNewOld(Context app_context, String type) {

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

    protected String getSection(Context app_Context, JSONObject data_JSOB) throws JSONException
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
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        }

        //SpannableStringBuilder intro = new SpannableStringBuilder(Html.fromHtml(data));
        return data;
    }

<<<<<<< HEAD
    protected String readFile(Context app_Context, String relativePath) throws IOException {
=======
    protected String readFile(Context app_Context, String relativePath) throws IOException
    {
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        String myData = "";


        //InputStream fis = app_Context.getDataDir().open("Prayer/MorningPrayer/Confessional/EN_BasicConfessional.txt");

<<<<<<< HEAD
        String fileName = app_Context.getFilesDir().getPath() + relativePath;
=======
        String fileName = app_Context.getFilesDir().getPath()+relativePath;
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

        File file = new File(fileName);
        //InputStream fis = app_Context.getDataDir().open("Prayer/MorningPrayer/Confessional/EN_BasicConfessional.txt");

        FileInputStream fis = new FileInputStream(file);
        //FileInputStream fis = openFileInput(fileName);
        DataInputStream in = new DataInputStream(fis);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String strLine;
<<<<<<< HEAD
        while ((strLine = br.readLine()) != null) {
=======
        while((strLine = br.readLine())!= null) {
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
            myData = myData + strLine;
        }
//
        br.close();
        in.close();
        fis.close();


<<<<<<< HEAD
        return myData;
    }

    protected void setAppLocale(String localeCode) {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();

        config.setLocale(new Locale(localeCode.toLowerCase()));


        resources.updateConfiguration(config, dm);
=======

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
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

    }


    public void onClickBtnLanguage() {

<<<<<<< HEAD
        if (language.equals("EN")) {
=======
        if(language.equals("EN")){
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
            language = "CY";
            this.setAppLocale("cy");
            this.setUpPrayer(prayerType);

<<<<<<< HEAD
        } else {
            language = "EN";
=======
        }
        else {
            language="EN";
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
            this.setAppLocale("EN");
            this.setUpPrayer(prayerType);
        }

        //This is necessary as the app doesn't update 'top level' resources!
        btn_Language.setText(getString(R.string.btn_Language));
    }

<<<<<<< HEAD
    public void onComplete(Result<String> result) {
        if (result instanceof Result.Success) {
            //  br_ViewModel.setValue(new SpannableStringBuilder(Html.fromHtml(((Result.Success<String>) result).data,Html.FROM_HTML_OPTION_USE_CSS_COLORS)));
            br_ViewModel.postValue((((Result.Success<String>) result).type), (((Result.Success<String>) result).data));
        } else {
            br_ViewModel.postValue("Error", "There was an error");
=======
    public void onComplete(Result<String> result)
    {
        if(result instanceof Result.Success) {
            //  br_ViewModel.setValue(new SpannableStringBuilder(Html.fromHtml(((Result.Success<String>) result).data,Html.FROM_HTML_OPTION_USE_CSS_COLORS)));
            br_ViewModel.postValue((((Result.Success<String>)result).type),(((Result.Success<String>)result).data));
        } else {
            br_ViewModel.postValue("Error","There was an error");
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        }

    }


    @Override
    public void onChanged(Object o) {
<<<<<<< HEAD
        myHandler.post(new Runnable() {
            public void run() {
                tv_Prayer.setText(Html.fromHtml(br_ViewModel.getPage(), Html.FROM_HTML_MODE_LEGACY));
            }
        });
    }

    public boolean checkPermissions() {
        if (PermissionChecker.checkSelfPermission(getContext(), Manifest.permission.INTERNET) == PermissionChecker.PERMISSION_GRANTED) {
=======
        tv_Prayer.setText(Html.fromHtml(br_ViewModel.getPage(),Html.FROM_HTML_SEPARATOR_LINE_BREAK_HEADING));

    }

    public boolean checkPermissions()
    {
        if(PermissionChecker.checkSelfPermission(getContext(), Manifest.permission.INTERNET) == PermissionChecker.PERMISSION_GRANTED ) {
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
            return true;
        } else {
            String[] permissionArrays = new String[]{Manifest.permission.INTERNET};
            requestPermissions(permissionArrays, REQUEST_CODE_ASK_PERMISSONS);
        }
        return false;
    }

    public void onRequestPermissions(int requestCode, @NonNull String permissions[]) {
        Toast.makeText(getContext(), "Permission Requested", Toast.LENGTH_SHORT).show();
    }


<<<<<<< HEAD
    @Override
    public void setBible(Result result) {

        if (result instanceof Result.Success) {
            this.bibleSet = Boolean.TRUE;
            this.bibleFound = Boolean.TRUE;
            this.bible = (Book) ((Result.Success) result).data;

        } else {
            this.bibleFound = Boolean.TRUE;
            this.bibleSet = Boolean.FALSE;
        }
    }

    @Override
    public void setJswordVerse(Result<String> result) {
        if (result instanceof Result.Success) {
            //  br_ViewModel.setValue(new SpannableStringBuilder(Html.fromHtml(((Result.Success<String>) result).data,Html.FROM_HTML_OPTION_USE_CSS_COLORS)));
            br_ViewModel.postValue((String) (((Result.Success) result).type), ((String) ((Result.Success) result).data));
        } else {
            br_ViewModel.postValue("Error", "There was an error");
        }


    }

    protected void setDisplayFont() {
        String currFont = sharedPrefs.getString("font", null);
        String currFontSize = sharedPrefs.getString("fontSize","14");
        AppDebug.log("TAG","Font Type "+currFont+ "Font Size:"+currFontSize);
        if(currFont != null) {
            if(currFont.contains(".otf")) {
                tv_Prayer.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"font/"+currFont));
                int currFontSizeInt = Integer.parseInt(currFontSize);
                tv_Prayer.setTextSize(currFontSizeInt);
            }
            else {
                tv_Prayer.setTypeface(Typeface.create(currFont, Typeface.NORMAL));
                tv_Prayer.setTextSize(Integer.parseInt(currFontSize));
            }
        }
        else {
            tv_Prayer.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
            tv_Prayer.setTextSize(Integer.parseInt(currFontSize));
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        this.setDisplayFont();
    }




=======
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
}