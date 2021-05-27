package com.churchinwales.prayer;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

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
    JSONObject jsonobj_Order = new JSONObject();
    String type = "Order";
    Boolean order = false;

    public BibleReadingsViewModel(JSONObject theOrder, String JSONSubtype)
    {
        super();
        Map temp = Collections.synchronizedMap(new HashMap());
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
        Map temp = Collections.synchronizedMap(new HashMap());
        document.setValue(temp);
        order = true;
    }

    public String getValue(@NotNull String key) {
        return String.valueOf(document.getValue().get(key));
    }

    public void setValue(String key, String value)
    {
        Map temp = document.getValue();
        try{
            jsonobj_Order.put(key,value);
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
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
    public void postValue(String key, String value)
    {
        Map temp = document.getValue();
        temp.put(key,value);
        try{
            jsonobj_Order.put(key,value);
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        document.postValue(temp);
    }

    public MutableLiveData<Map> getObservable()
    {
        return document;
    }

    public String getPage(){
        String page = "";
        String line = "";
        Log.v("TAG", "Getting page...");

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
                Log.v("TAG", "Iterating...");
                while (keys.hasNext()) {
                    String item = (String) keys.next();
                    Log.v("TAG", page + item);
                    page = page + document.getValue().get(item);

                }
            }
        }

       return page;
    }


}
