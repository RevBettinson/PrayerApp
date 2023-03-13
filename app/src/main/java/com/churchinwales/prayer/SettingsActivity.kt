package com.churchinwales.prayer


import android.graphics.Typeface
import android.graphics.fonts.Font
import android.graphics.fonts.SystemFonts
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            val context = preferenceManager.context
/*
            val listPreference = ListPreference(context).apply {
                key = "Font Test"
                title = "Test Title"
                entries = arrayOf("Test 1", "Test 2")
                entryValues = arrayOf("1", "2")
            }

            preferenceScreen.addPreference(listPreference)
*/
        }



    }

}