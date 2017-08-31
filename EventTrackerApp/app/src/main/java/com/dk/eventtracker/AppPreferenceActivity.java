package com.dk.eventtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.dk.eventtracker.helpers.Util;

/**
 * Created by Dalibor on 31.8.2017..
 */

public class AppPreferenceActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        (new Util()).setLanguage(this);
        this.recreate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AppPreferenceFragment()).commit();
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    public static class AppPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.app_preferences);
        }
    }
}
