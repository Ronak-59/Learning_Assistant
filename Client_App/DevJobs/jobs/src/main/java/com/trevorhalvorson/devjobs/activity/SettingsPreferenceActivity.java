package com.trevorhalvorson.devjobs.activity;

import android.preference.PreferenceActivity;

import com.trevorhalvorson.devjobs.R;
import com.trevorhalvorson.devjobs.fragment.SettingsPreferenceFragment;

import java.util.List;

/**
 * Created by trevo on 9/22/2015.
 */
public class SettingsPreferenceActivity extends PreferenceActivity {
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.headers_preference, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return SettingsPreferenceFragment.class.getName().equals(fragmentName);
    }
}