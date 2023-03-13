package com.churchinwales.prayer;

<<<<<<< HEAD
=======
import android.util.Log;
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

<<<<<<< HEAD

import java.util.Iterator;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BibleReadingsViewModel extends ViewModel {

    MutableLiveData<ConcurrentHashMap> document = new MutableLiveData<ConcurrentHashMap>();
=======
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

public class BibleReadingsViewModel extends ViewModel {

    MutableLiveData<Map> document = new MutableLiveData<Map>();
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
    JSONObject jsonobj_Order = new JSONObject();
    String type = "Order";
    Boolean order = false;

    public BibleReadingsViewModel(JSONObject theOrder, String JSONSubtype)
    {
        super();
<<<<<<< HEAD
        //Map temp = Collections.synchronizedMap(new HashMap());
        ConcurrentHashMap<String,String> temp = new ConcurrentHashMap<String,String>();
=======
        Map temp = Collections.synchronizedMap(new HashMap());
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        document.setValue(temp);
        //jsonobj_Order = theOrder;
        jsonobj_Order = new JSONObject();

        type = JSONSubtype;
        order = true;
    }


    public BibleReadingsViewModel()
    {
        super();
        jsonobj_Order = new JSONObject();
<<<<<<< HEAD
       // Map temp = Collections.synchronizedMap(new HashMap());
        ConcurrentHashMap<String,String> temp = new ConcurrentHashMap<String,String>();
=======
        Map temp = Collections.synchronizedMap(new HashMap());
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        document.setValue(temp);
        order = true;
    }

    public String getValue(@NotNull String key) {
        return String.valueOf(document.getValue().get(key));
    }

<<<<<<< HEAD
    public synchronized void setValue(String key, String value)
    {
        ConcurrentHashMap<String, String> temp = document.getValue();
=======
    public void setValue(String key, String value)
    {
        Map temp = document.getValue();
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        try{
            jsonobj_Order.put(key,value);
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
        if (temp == null) throw new AssertionError();
=======
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        temp.put(key,value);
        document.setValue(temp);
    }
/**
    public void setAppendValue(String value) {
        document.setValue(document.getValue()+value);
    }

    public void postAppendValue(String value)
    {
        document.postValue(document.getValue()+value);
    }
**/
<<<<<<< HEAD
    public synchronized void postValue(String key, String value)
    {
        ConcurrentHashMap temp = document.getValue();
=======
    public void postValue(String key, String value)
    {
        Map temp = document.getValue();
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        temp.put(key,value);
        try{
            jsonobj_Order.put(key,value);
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        document.postValue(temp);
    }

<<<<<<< HEAD
    public MutableLiveData<ConcurrentHashMap> getObservable()
=======
    public MutableLiveData<Map> getObservable()
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
    {
        return document;
    }

    public String getPage(){
        String page = "";
        String line = "";
<<<<<<< HEAD
        AppDebug.log("TAG", "Getting page...");
=======
        Log.v("TAG", "Getting page...");
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

        if(order) {

                //JSONObject jsonOrder = jsonobj_Order.optJSONObject(type);
                JSONObject jsonOrder = jsonobj_Order;
                Iterator keys = jsonOrder.keys();

                while (keys.hasNext()) {
                    Object key = keys.next();

                    try {
                        String name = jsonOrder.getString((String) key);

                        page = page + document.getValue().get((String)key);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

        } else {
            Set s = document.getValue().keySet();
            synchronized (document.getValue()) {
                Iterator keys = s.iterator();
<<<<<<< HEAD
                AppDebug.log("TAG", "Iterating...");
                while (keys.hasNext()) {
                    String item = (String) keys.next();
                    AppDebug.log("TAG", page + item);
=======
                Log.v("TAG", "Iterating...");
                while (keys.hasNext()) {
                    String item = (String) keys.next();
                    Log.v("TAG", page + item);
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
                    page = page + document.getValue().get(item);

                }
            }
        }

       return page;
    }


}
