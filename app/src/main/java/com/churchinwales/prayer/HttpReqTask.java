package com.churchinwales.prayer;

<<<<<<< HEAD

import android.text.TextUtils;
import android.util.Log;


import org.crosswire.jsword.book.Book;
import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.book.Books;
import org.crosswire.jsword.passage.Key;
import org.crosswire.jsword.passage.NoSuchKeyException;


import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;

import javax.net.ssl.HttpsURLConnection;
/*
* Removed due to Kotlin complaining it can't find them
*
* /
interface app_BiblePericope_Callback<T> {
    public void onComplete(Result<T> result);
}

interface setJswordBible<T> {
    public void setBible(Result<T> result);


}

interface setJswordVerse<T> {
    public void setJswordVerse(Result<T> result);

}
*/
=======
import android.Manifest;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;

import javax.net.ssl.HttpsURLConnection;

interface app_BiblePericope_Callback<T> {
    void onComplete(Result<T> result);
}


>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
public class HttpReqTask  {

    private final ExecutorService executor;

    public HttpReqTask(ExecutorService executor) {
        this.executor = executor;

    }


    protected Result<String> request(String pericope, String section) {
<<<<<<< HEAD

        HttpURLConnection https = null;
        StringBuilder myData = new StringBuilder();
=======
        URLConnection urlConnection = null;
        HttpURLConnection https = null;
        String myData ="";
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

        Result myResult;

        try {
            URL url = new URL("https://bible.oremus.org?version=NRSVAE&passage="+ TextUtils.htmlEncode(pericope));


            https = (HttpsURLConnection) url.openConnection();

            // https.connect();

            int code = https.getResponseCode();
            if (code != 200) {
<<<<<<< HEAD
                AppDebug.log("TAG", "No Connection");
=======
                Log.v("TAG", "No Connection");
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
                //txt_Bible.setText("No Connection");
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(https.getInputStream()));

<<<<<<< HEAD
            String line;

            boolean ignore = Boolean.TRUE;

            while ((line = rd.readLine()) != null) {

                //AppDebug.log("NSRV", line);

                if(line.toLowerCase().startsWith("<div class=\"bibletext\">")) {
                    ignore = Boolean.FALSE;
                }
                if(line.toLowerCase().startsWith("</div>")) {
                    ignore=Boolean.TRUE;
                }
                if(line.toLowerCase().startsWith("<cite")) {
                        myData.append("<br><br>");
                        ignore=Boolean.TRUE;
                }

                if(!ignore) {
                    myData.append(line);

                }
            }
            if(myData.toString().equals("")) {
                myData = new StringBuilder("Error getting bible reading for:"+pericope);
            }

            myResult = new Result.Success<>(section,myData.toString());
=======
            String line = "";

            Boolean ignore = true;

            while ((line = rd.readLine()) != null) {

                Log.v("TAG", line);

                if(line.toLowerCase().startsWith("<div class=\"bibletext\">")) {
                    ignore = false;
                }
                if(line.toLowerCase().startsWith("</div>")) {
                    ignore=true;
                }
                if(line.toLowerCase().startsWith("<cite")) {
                        myData = myData+"<br><br>";
                        ignore=false;
                }

                if(!ignore) {
                    myData = myData + line;

                }
            }
            if(myData.equals("")) {
                myData = "Error getting bible reading for:"+pericope;
            }

            myResult = new Result.Success<String>(section,myData);
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1



        } catch (Exception e) {
            e.printStackTrace();
<<<<<<< HEAD
            //myData= new StringBuilder("Exception!");
=======
            myData = "Exception!";
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
            myResult = new Result.Error(e);
        } finally {
            if (https != null) {
                https.disconnect();
            }
        }

        return myResult;
    }

<<<<<<< HEAD
    public Book getBible(String bibleName) {

        return Books.installed().getBook(bibleName);
    }

    public void getBibleBook(final String bibleName, setJswordBible<Book> callback) {
        executor.execute(new Runnable() {
           public void run() {

               Book bible = getBible(bibleName);
               Result success = new Result.Success(bibleName, bible);
               callback.setBible(success);


           }
        });

    }

    public void getJswordVerse(final Book bible, String theKey, String section, setJswordVerse<String> callback) {
        AppDebug.log("JSWORDVerse",theKey);
        executor.execute(new Runnable() {
            public void run() {
                try {
                        //this produces a series of keys

                        Key test = bible.getKey(theKey);

                /*
                We then get the key iterator (which is not listed in the docs!)
                and use that to pull out all the verses we need, verse by verse.
                */
                        Iterator<Key> testKey = test.iterator();

                        StringBuilder text =  new StringBuilder();
                        while (testKey.hasNext()) {
                            Key theKey = testKey.next();
                            AppDebug.log("TAG", theKey.getName() + " " + bible.getRawText(theKey));
                            text.append(bible.getRawText(theKey));

                        }

                        Result<String> result = new Result.Success<>(section, text.toString());
                        callback.setJswordVerse(result);
                }
                catch(BookException e) {
                    Result error = new Result.Error(e);
                    callback.setJswordVerse(error);
                    e.printStackTrace();
                }
                catch(NoSuchKeyException e) {
                    Result error = new Result.Error(e);
                    e.printStackTrace();
                    callback.setJswordVerse(error);
                }
            }
        });

    }
=======


>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1


    public void makeBibleRequest(final String pericope,String section, app_BiblePericope_Callback<String> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Result<String> result = request(pericope, section);
                    callback.onComplete(result);
                }
                catch(Exception e) {
                    Result<String> errorResult = new Result.Error<>(e);
                    callback.onComplete(errorResult);
                }
            }
        });

    }

}
