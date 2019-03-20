package com.trevorhalvorson.devjobs.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.trevorhalvorson.devjobs.R;

/**
 * Created by trevo on 9/22/2015.
 */
public class SettingsPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_preference);
    }

}


