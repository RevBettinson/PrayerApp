package com.churchinwales.prayer;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.util.Log;

import androidx.annotation.RequiresApi;

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
import java.util.Locale;

public class Helper {

    String myLectionary ="";

    public Helper() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    /**
     * Shuould Taake in a year
     *
     * Should perhaps return a JsonOBject?
     */
    public SpannableStringBuilder getLectionaryText(Context app_Context){

        SpannableStringBuilder contents = new SpannableStringBuilder("");

        try {

            if(myLectionary=="") {
                myLectionary = this.readAsset(app_Context, "lectionary-YearTwo.json");
            }
            String myData = myLectionary;

            String season="ADVENT";
            int weekOfSeason=1;
            String dayOfWeek="Monday";

            JSONObject jsonRootObject = new JSONObject(myData);

            Calendar cal = Calendar.getInstance();
            Calendar easter = new Calendar.Builder()
                    .setDate(2021, 3, 4)
                    .build();

            if( cal.compareTo(easter) > 0) {
                contents.append(Html.fromHtml("Date is after Easter<br>"));

                //   cal.add(Calendar.YEAR, - easter.get(Calendar.YEAR));
                //    cal.add(Calendar.MONTH, - easter.get(Calendar.MONTH));
                //    cal.add(Calendar.DAY_OF_MONTH, - easter.get(Calendar.DAY_OF_MONTH));
                contents.append(Html.fromHtml("Current Date: "+cal.get(Calendar.YEAR)+":"+cal.get(Calendar.MONTH)+ ":"+cal.get(Calendar.DAY_OF_MONTH)+"<BR>"));

                long weeks = cal.getTimeInMillis() - easter.getTimeInMillis();

                Calendar newCal = new Calendar.Builder().setInstant(weeks).build();

                int weeksSinceEaster = newCal.get(Calendar.WEEK_OF_YEAR);

                dayOfWeek = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

                // weeksSinceEaster = weeksSinceEaster / (24 * 60 * 60 * 1000);

                if(weeksSinceEaster <= 6) {
                    season = "EASTER";
                    weekOfSeason = weeksSinceEaster;
                }
                else {
                    season = "TRINITY";
                    weekOfSeason = weeksSinceEaster -6;
                }

                contents.append(Html.fromHtml("Weeks Since Easter: "+weeksSinceEaster+"<br>"));
                contents.append(Html.fromHtml("Season: "+season+"<BR>"));
                contents.append(Html.fromHtml("Day:"+dayOfWeek+"<BR>"));
            } else {
                if (cal.compareTo(easter) < 0) {
                    contents.append("Date is before Easter");
                }
            }

            Log.v("TAG","Season:"+season+" Week:"+String.valueOf(weekOfSeason)+ " Day:"+dayOfWeek);

            JSONObject jsonObject = jsonRootObject.optJSONObject(season);
            JSONObject week =jsonObject.optJSONObject(String.valueOf(weekOfSeason));

            if(dayOfWeek.equalsIgnoreCase("Sunday")) {
                dayOfWeek = "Saturday";
            }

            JSONObject day = week.optJSONObject(dayOfWeek);
            JSONObject prayer =  day.optJSONObject("MorningPrayer");

            contents.append(Html.fromHtml("<br>"));
            contents.append(Html.fromHtml("Morning Prayer<br>"));
            contents.append(Html.fromHtml("Psalm: "+prayer.getString("Psalm")+"<BR>"));
            contents.append(Html.fromHtml("OT: "+prayer.getString("OT")+"<br>"));
            contents.append(Html.fromHtml("NT: "+prayer.getString("NT")+"<BR>"));
            contents.append(Html.fromHtml("<br><br>"));

            prayer =  day.optJSONObject("EveningPrayer");

            contents.append(Html.fromHtml("Evening Prayer<br>"));
            contents.append(Html.fromHtml("Psalm: "+prayer.getString("Psalm")+"<BR>"));
            contents.append(Html.fromHtml("OT: "+prayer.getString("OT")+"<br>"));
            contents.append(Html.fromHtml("NT: "+prayer.getString("NT")+"<BR>"));

        }

        catch(IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return contents;


    }


    public JSONObject getLectionaryJson(Context app_Context, String prayerTime){

        JSONObject prayer = new JSONObject();

        try {

            if(myLectionary=="") {
                myLectionary = this.readAsset(app_Context, "lectionary-YearTwo.json");
            }
            String myData = myLectionary;

            String season="ADVENT";
            int weekOfSeason=1;
            String dayOfWeek="Monday";

            JSONObject jsonRootObject = new JSONObject(myData);

            Calendar cal = Calendar.getInstance();
            Calendar easter = new Calendar.Builder()
                    .setDate(2021, 3, 4)
                    .build();

            if( cal.compareTo(easter) > 0) {

                long weeks = cal.getTimeInMillis() - easter.getTimeInMillis();

                Calendar newCal = new Calendar.Builder().setInstant(weeks).build();

                int weeksSinceEaster = newCal.get(Calendar.WEEK_OF_YEAR);

                dayOfWeek = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

                // weeksSinceEaster = weeksSinceEaster / (24 * 60 * 60 * 1000);

                if(weeksSinceEaster <= 6) {
                    season = "EASTER";
                    weekOfSeason = weeksSinceEaster;
                }
                else {
                    season = "TRINITY";
                    weekOfSeason = weeksSinceEaster -6;
                }


            } else {
                if (cal.compareTo(easter) < 0) {
                   Log.v("TAG","Date is before Easter");
                }
            }

            Log.v("TAG","Season:"+season+" Week:"+String.valueOf(weekOfSeason)+ " Day:"+dayOfWeek);

            JSONObject jsonObject = jsonRootObject.optJSONObject(season);
            JSONObject week =jsonObject.optJSONObject(String.valueOf(weekOfSeason));
            if(dayOfWeek.equalsIgnoreCase("Sunday")) {
                dayOfWeek = "Saturday";
            }
            JSONObject day = week.optJSONObject(dayOfWeek);



            if((prayerTime == "MP") || (prayerTime.equalsIgnoreCase("morningprayer"))) {
                prayer = day.optJSONObject("MorningPrayer");
            }
            else {
                prayer = day.optJSONObject("EveningPrayer");
            }
        }

        catch(IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return prayer;


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

    public String readAsset(Context app_Context, String relativePath) throws IOException
    {
        String myData = "";


        //InputStream fis = app_Context.getDataDir().open("Prayer/MorningPrayer/Confessional/EN_BasicConfessional.txt");

        InputStream file= app_Context.getAssets().open(relativePath);

        int size = file.available();
        byte[] buffer = new byte[size];
        file.read(buffer);
        myData = new String(buffer);

        file.close();

        return myData;
    }
}
