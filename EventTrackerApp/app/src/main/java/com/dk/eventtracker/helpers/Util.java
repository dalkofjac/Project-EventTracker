package com.dk.eventtracker.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import java.util.Locale;

/**
 * Created by Dalibor on 31.8.2017..
 */

public class Util {
    public void setLanguage(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String lang = preferences.getString("pref_lang", "en");

        Configuration config = new Configuration(context.getResources().getConfiguration());
        config.setLocale(new Locale(lang));
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
}
