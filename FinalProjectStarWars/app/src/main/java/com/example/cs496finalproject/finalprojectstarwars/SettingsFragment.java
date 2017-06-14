package com.example.cs496finalproject.finalprojectstarwars;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.EditText;

/**
 * Created by Godtop on 6/3/2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey){
        addPreferencesFromResource(R.xml.prefs);
        EditTextPreference userPref1 = (EditTextPreference)findPreference(getString(R.string.pref_swapi_search_key1));
        EditTextPreference userPref2 = (EditTextPreference)findPreference(getString(R.string.pref_swapi_search_key2));
        userPref1.setSummary(userPref1.getText());
        userPref2.setSummary(userPref2.getText());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key){
        if (key.equals(getString(R.string.pref_swapi_search_key1))){
            EditTextPreference userPref1 = (EditTextPreference)findPreference(key);
            userPref1.setSummary(userPref1.getText());
        }
        if (key.equals(getString(R.string.pref_swapi_search_key2))){
            EditTextPreference userPref2 = (EditTextPreference)findPreference(key);
            userPref2.setSummary(userPref2.getText());
        }

    }

    @Override
    public void onResume(){
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

}
