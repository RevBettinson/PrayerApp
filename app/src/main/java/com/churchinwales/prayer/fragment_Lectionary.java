package com.churchinwales.prayer;

import android.annotation.SuppressLint;
<<<<<<< HEAD

=======
import android.content.Context;
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

<<<<<<< HEAD

import android.text.Html;
import android.text.SpannableStringBuilder;

=======
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.util.Log;
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

<<<<<<< HEAD
=======
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_Lectionary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_Lectionary extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Helper theHelper;

    TextView tv_Lectionary;
<<<<<<< HEAD


=======
    String myLectionary="";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

    public fragment_Lectionary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment nav_Lectionary.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_Lectionary newInstance(String param1, String param2) {
        fragment_Lectionary fragment = new fragment_Lectionary();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD

=======
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        theHelper = new Helper();
    }

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lectionary, container, false);
        getActivity().setTitle(getString(R.string.app_Lectionary));

        tv_Lectionary = (TextView)rootView.findViewById(R.id.txt_LectionaryOutput);

       // tv_Lectionary.append("Hello World");

        updatePage();

        return rootView;

    }

    /**
     * NOTE: Month is one digit down, January is month 0, not 1
     *
     */


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updatePage(){

<<<<<<< HEAD
        SpannableStringBuilder contents = new SpannableStringBuilder("");
        try {
            JSONObject mp = theHelper.getLectionaryJson(getActivity().getApplicationContext(), "MP");
            JSONObject ep = theHelper.getLectionaryJson(getActivity().getApplicationContext(), "EP");

            String[] season =new Lectionary().getSeason();

            contents.append(Html.fromHtml("<br><H1>Season: "+ season[Lectionary.SEASON] + " Week:"+season[Lectionary.WEEKOFSEASON]+"</H1>", Html.FROM_HTML_MODE_LEGACY));

            contents.append(Html.fromHtml("<H2>"+new Lectionary().getDayOfWeek()+"</H2>", Html.FROM_HTML_MODE_LEGACY));

            JSONObject prayer = mp;

            contents.append(Html.fromHtml("<br>", Html.FROM_HTML_MODE_LEGACY));
            contents.append(Html.fromHtml("Morning Prayer<br>", Html.FROM_HTML_MODE_LEGACY));
            contents.append(Html.fromHtml("Psalm: " + prayer.getString("Psalm") + "<BR>", Html.FROM_HTML_MODE_LEGACY));
            contents.append(Html.fromHtml("OT: " + prayer.getString("OT") + "<br>", Html.FROM_HTML_MODE_LEGACY));
            contents.append(Html.fromHtml("NT: " + prayer.getString("NT") + "<BR>", Html.FROM_HTML_MODE_LEGACY));
            contents.append(Html.fromHtml("<br><br>", Html.FROM_HTML_MODE_LEGACY));

            prayer = ep;

            contents.append(Html.fromHtml("Evening Prayer<br>", Html.FROM_HTML_MODE_LEGACY));
            contents.append(Html.fromHtml("Psalm: " + prayer.getString("Psalm") + "<BR>", Html.FROM_HTML_MODE_LEGACY));
            contents.append(Html.fromHtml("OT: " + prayer.getString("OT") + "<br>", Html.FROM_HTML_MODE_LEGACY));
            contents.append(Html.fromHtml("NT: " + prayer.getString("NT") + "<BR>", Html.FROM_HTML_MODE_LEGACY));
        }
        catch (JSONException e){
            contents.append(e.toString());

        }

=======
        SpannableStringBuilder contents = theHelper.getLectionaryText(getActivity().getApplicationContext());
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        tv_Lectionary.append(contents);
    }
}