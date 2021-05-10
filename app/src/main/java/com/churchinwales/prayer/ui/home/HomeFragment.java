package com.churchinwales.prayer.ui.home;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.churchinwales.prayer.Helper;
import com.churchinwales.prayer.R;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        /**
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
**/
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        final TextView textView = root.findViewById(R.id.text_home);
        Helper myHelper = new Helper();
        String todo="";
        try {
            todo = myHelper.readAsset(getActivity().getApplicationContext(), "Todo.txt");
        }
        catch(IOException e){
            todo = getString(R.string.Error)+" "+getString(R.string.FileNotFound)+": ToDo";
        }

        textView.setText(todo);
        textView.setMovementMethod(new ScrollingMovementMethod());
        /**
        homeViewModel.setText(todo);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                    public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
        });
**/

        textView.setText(new SpannableStringBuilder(Html.fromHtml(todo)));
        textView.append("Version: "+getString(R.string.app_version));

        return root;
    }

}