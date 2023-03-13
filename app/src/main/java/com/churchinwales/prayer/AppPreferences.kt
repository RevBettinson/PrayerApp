package com.churchinwales.prayer

import android.content.Context
import android.content.SharedPreferences


class AppPreference(val context: Context) {

    private val PREFS_NAME :String = context.getString(R.string.App_Key_SharedPref)
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val defaultPref: HashMap<String,String> = HashMap<String,String> ()


    fun save(KEY_NAME: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(KEY_NAME, value)

        editor.apply()
    }


    fun getValueString(KEY_NAME: String): String? {

        return if (sharedPref.contains(KEY_NAME)) {
            sharedPref.getString(KEY_NAME, null)
        } else defaultPref[KEY_NAME]
    }

    fun setupDefaults() {
        defaultPref["Font"] = "Arial"
        defaultPref["APP_INST_DATE"] = "0"

    }
}