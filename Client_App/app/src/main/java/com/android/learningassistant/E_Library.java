package com.android.learningassistant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ames.books.BookListActivity;


public class E_Library extends AppCompatActivity {

    RadioGroup databasegroup;
    Button libraryEnter;
    WebView w;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_e__library);

        databasegroup = (RadioGroup)findViewById(R.id.radiogrp_library);
        libraryEnter = (Button)findViewById(R.id.joinlibrary);
        pd = new ProgressDialog(E_Library.this);
        pd.setCancelable(false);
        pd.setIndeterminate(true);
        pd.setMessage("Entering SVKM Library...");
        databasegroup.check(databasegroup.getChildAt(0).getId());
        libraryEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String radiovalue = ((RadioButton)findViewById(databasegroup.getCheckedRadioButtonId())).getText().toString();
                if(radiovalue.equalsIgnoreCase("Google Books")){
                    startActivity(new Intent(E_Library.this, BookListActivity.class));
                }
                else if(radiovalue.equalsIgnoreCase("SVKM Digital Library")){
                    pd.show();
                    w = new WebView(E_Library.this);

                    w.setWebViewClient(new WebViewClient() {
                        public void onPageFinished(final WebView view, String url) {
                            pd.dismiss();
                        }
                        });
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { WebView.enableSlowWholeDocumentDraw(); }
                    setContentView(w);
                    w.loadUrl("http://ezproxy.svkm.ac.in:2048/login");
                    WebSettings webSettings = w.getSettings();
                    webSettings.setUseWideViewPort(true);
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setJavaScriptEnabled(true);
                    webSettings.setBuiltInZoomControls(true);
                    webSettings.setDisplayZoomControls(false);

                }
                else{

                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}