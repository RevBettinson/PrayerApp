package com.churchinwales.prayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    TextView tv_Prayer;
    TextView tv_Title;
    //Note: this should use Androids built-in language stuffs
    String language="EN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context app_Context = getApplicationContext();
        //Loading Dialogue Start Here
        ResourceLoader.unzipFromAssets(app_Context,"Prayer.zip","");

        tv_Prayer = (TextView)findViewById(R.id.txt_MainView);
        tv_Prayer.setMovementMethod(new ScrollingMovementMethod());
        tv_Title = (TextView)findViewById(R.id.txt_title);

        //This needs to be a translatable string. TODO
        tv_Title.setText("Morning Prayer");
        this.setUpPrayer();
        //Loading Dialogue End here
    }


    /**
     * Loads a layout file, currently hard coded.
     *
     * Loops through the layout file, and loads the relevant sections.
     *
     * If the files are not found, prints an error into the document that the section
     * cannot be found.
     */
    protected void setUpPrayer()
    {
        Context app_Context = getApplicationContext();

        SpannableStringBuilder myDocument = new SpannableStringBuilder("");

        String myData = "";

        try {
            myData = readFile(app_Context, "/Prayer/Layout/MorningPrayer.json");
        }
        catch (IOException e) {
            e.printStackTrace();
            myDocument.append("FATAL: can't find layout file");
        }


        StringBuilder data = new StringBuilder();
        try {
            JSONObject jsonRootObject = new JSONObject(myData);
            JSONObject jsonObject = jsonRootObject.optJSONObject("MorningPrayer");



            Iterator keys = jsonObject.keys();


            while(keys.hasNext()) {
                Object key = keys.next();

                Log.v("TAG",((String)key));

                //Will still pick up the key "morning Prayer"!
                if(((String)key).equals("Name")){
                    JSONObject data_JSOB = jsonObject.getJSONObject((String) key);
                    String name = data_JSOB.getString(language);
                    name=name+"<br><br>";
                    myDocument.append(Html.fromHtml(name));
                }
                if(((String)key).startsWith("Section")){
                    JSONObject data_JSOB = jsonObject.getJSONObject((String) key);
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

    private SpannableStringBuilder getBibleReading(Context app_context, String type) {

        String reading = "";
        String myData = "";

        try {
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

            JSONArray jsonRootArray = new JSONArray(myData);

            //work out the week of the year, starting from the first Sunday of Advent!
            Calendar cal = Calendar.getInstance();

            int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);


            //requires calculations for ADVENT
            dayOfYear = dayOfYear;

            Log.v("TAG", "Day Of Year:" + dayOfYear);


            JSONObject myReadings = (JSONObject) jsonRootArray.get(dayOfYear);

            //reading = myReadings.getString("year");
            reading = reading + "<BR>";

            if (type.equalsIgnoreCase("OT")) {
                //should be a string resource!
                reading = reading + "Old Testament Reading:<br>";
                reading = reading + ((JSONObject) myReadings.getJSONObject("lessons")).getString("first");
            }

            if (type.equalsIgnoreCase("NT")) {
                //should be a string resource!
                reading = reading +"New Testament Reading:<br>";
                reading = reading + ((JSONObject) myReadings.getJSONObject("lessons")).getString("second");
            }

            if (type.equalsIgnoreCase("GP")) {
                //should be a string resource!
                reading = reading +"Gospel Reading:<br>";
                reading = reading + ((JSONObject) myReadings.getJSONObject("lessons")).getString("gospel");
            }

            reading=reading+"<BR><BR>";

        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(JSONException e) {
            e.printStackTrace();
        }

        return new SpannableStringBuilder(Html.fromHtml(reading));

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
            data = "Error:"+data_JSOB.getString("Name")+" Not found!<br><br>";
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
}