package com.example.matth.flohmarkt;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

public class Prefs extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.prefferences, s);
        Preference p=findPreference("uname");
        EditTextPreference et= (EditTextPreference) p;
        if(!et.getText().equals("-")){
            et.setSummary(et.getText());
        }

        p=findPreference("psw");
        et= (EditTextPreference) p;
        if(!et.getText().equals("-")){
            et.setSummary("******");
        }
    }

    @Override
    public void onResume() {
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences s, String key) {
                Preference p=findPreference(key);
                if(p.getTitle().toString().toLowerCase().equals("password")){
                    p.setSummary("******");
                }else{
                    p.setSummary(((EditTextPreference) p).getText());
                }
            }
        });
        super.onResume();
    }
}
