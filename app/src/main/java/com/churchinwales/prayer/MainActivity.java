package com.churchinwales.prayer;

import android.content.Context;
<<<<<<< HEAD
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import android.view.Menu;


import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;

=======
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

<<<<<<< HEAD
import static java.lang.String.valueOf;

=======
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

public class MainActivity extends AppCompatActivity implements
    NavigationView.OnNavigationItemSelectedListener
{

<<<<<<< HEAD
    private AppBarConfiguration mAppBarConfiguration;

=======
    private ExecutorService executorService = Executors.newFixedThreadPool(4);
    private AppBarConfiguration mAppBarConfiguration;
    private ProgressBar spinner;
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context app_Context = getApplicationContext();
        //Loading Dialogue Start Here
<<<<<<< HEAD
        AppPreference preferences = new AppPreference(this);


        Boolean RunInstallZip = false;
        PackageInfo pInfo = null;

       //AppDebug.log("TAG", preferences.getValueString("APP_INST_DATE"));

        try {
            pInfo = app_Context.getPackageManager().getPackageInfo(getPackageName(), 0);

            if(!TextUtils.isEmpty(preferences.getValueString("APP_INST_DATE"))) {
                if (Long.parseLong(preferences.getValueString("APP_INST_DATE")) > 0) {
                    if (Long.parseLong(preferences.getValueString("APP_INST_DATE")) <= pInfo.firstInstallTime) {
                        RunInstallZip = true;
                    }
                    if (Long.parseLong(preferences.getValueString("APP_INST_DATE")) != pInfo.lastUpdateTime) {
                        RunInstallZip = true;
                    }

                }
            }
            else {
                RunInstallZip = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            RunInstallZip = true;
            e.printStackTrace();
        }

        if (RunInstallZip)
        {
            if(pInfo != null) {
                preferences.save("APP_INST_DATE", String.valueOf(pInfo.lastUpdateTime));
            }
            ResourceLoader.unzipFromAssets(app_Context, "Prayer.zip", "");
            ResourceLoader.unzipFromAssets(app_Context, "WelBeiblNet.zip", app_Context.getFilesDir().getPath() + "/JSWORD");
            ResourceLoader.unzipFromAssets(app_Context, getString(R.string.app_EnglishBibleJswordZipName), app_Context.getFilesDir().getPath() + "/JSWORD");
        }

        AppDebug.log("TAG", "Install Time In Millis:" + preferences.getValueString("APP_INST_DATE"));


    //setContentView(R.layout.fragment_prayer);

        setContentView(R.layout.activity_main);
        //setContentView(R.layout.fragment_cardview);
=======
        ResourceLoader.unzipFromAssets(app_Context,"Prayer.zip","");

        //setContentView(R.layout.fragment_prayer);
        setContentView(R.layout.activity_main);
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //spinner = (ProgressBar) findViewById(R.id.ProgressBar1);
        //spinner.setVisibility(View.GONE);
<<<<<<< HEAD
        /*
=======
        /**
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
<<<<<<< HEAD
         */

=======
         **/
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
<<<<<<< HEAD
                R.id.fragment_cardview, R.id.fragment_Prayer, R.id.id_EveningPrayer, R.id.fragment_Lectionary)
=======
                R.id.homeFragment, R.id.fragment_Prayer, R.id.id_EveningPrayer, R.id.fragment_Lectionary)
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
                .setOpenableLayout(drawer)
                .build();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
        //(onNavigationItemSelected())



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

<<<<<<< HEAD
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        Log.v("TAG", "On Navigation Item Selected");
        Bundle args = new Bundle();

        int destination=R.id.fragment_cardview;

        /*
        if (id == R.id.homeFragment) {
          //fragment = findViewById(R.id.homeFragment);
           destination = R.id.homeFragment;
        }*/

        if (id == R.id.fragment_cardview) {
            //fragment = findViewById(R.id.homeFragment);
            destination = R.id.fragment_cardview;
            mToolbar.setTitle(R.string.app_Welcome);
=======
        Log.v("TAG", "On Navigation Item Selected");
        Bundle args = new Bundle();

        int destination=R.id.homeFragment;

        if (id == R.id.homeFragment) {
          //fragment = findViewById(R.id.homeFragment);
           destination = R.id.homeFragment;
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        }

        if (id == R.id.fragment_Prayer) {

            args.putString("Type","MorningPrayer");
            destination=R.id.fragment_Prayer;
<<<<<<< HEAD
            mToolbar.setTitle(getString(R.string.app_MorningPrayer));
=======
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        }

        if (id == R.id.id_EveningPrayer) {
            args.putString("Type","EveningPrayer");
            destination=R.id.fragment_Prayer;
<<<<<<< HEAD
            mToolbar.setTitle(getString(R.string.app_EveningPrayer));
=======
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        }

        if(id== R.id.nav_Lectionary) {
            destination = R.id.fragment_Lectionary;
        }
        if(id == R.id.fragment_oremus){
            destination = R.id.fragment_oremus;
        }

<<<<<<< HEAD
        if(id == R.id.fragment_JSWORD) {
            destination = R.id.fragment_JSWORD;
        }

=======
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

        Navigation.findNavController(this,R.id.nav_host_fragment).navigate(destination,args);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

<<<<<<< HEAD
        if(id == R.id.id_EveningPrayer) {
            mToolbar.setTitle(getString(R.string.app_EveningPrayer));
        }

        if(id == R.id.fragment_Prayer) {
            mToolbar.setTitle(getString(R.string.app_MorningPrayer));
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        AppDebug.log("Menu",valueOf(id));
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

=======
        return true;
    }

>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
}