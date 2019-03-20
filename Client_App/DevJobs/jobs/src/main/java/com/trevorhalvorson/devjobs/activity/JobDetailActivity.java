package com.trevorhalvorson.devjobs.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.trevorhalvorson.devjobs.R;
import com.trevorhalvorson.devjobs.fragment.JobWebViewFragment;
import com.trevorhalvorson.devjobs.model.Job;


public class JobDetailActivity extends AppCompatActivity {
    private static final String ARG_JOB_KEY = "job_key";

    private boolean mWebViewPref;
    private String mJobUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        mWebViewPref = prefs.getBoolean(getString(R.string.wv_key), true);

        Intent intent = getIntent();
        Job job = (Job) intent.getSerializableExtra(ARG_JOB_KEY);

        if (job != null) {
            mJobUrl = job.getUrl();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        TextView descriptionTextView = (TextView) findViewById(R.id.description_text_view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(job.getTitle());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        descriptionTextView.setText(Html.fromHtml(job.getDescription()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
            return true;
        } else if (i == R.id.menu_item_web) {
            if (!mWebViewPref) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(mJobUrl));
                startActivity(intent);
            } else {
                Fragment jobWebViewFragment = JobWebViewFragment.newInstance(mJobUrl);
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.detail_coord_layout, jobWebViewFragment)
                        .commit();
            }
            return false;
        } else if (i == R.id.menu_item_share) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, mJobUrl);
            shareIntent.setType("text/plain");
            startActivity(shareIntent);
            return false;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
