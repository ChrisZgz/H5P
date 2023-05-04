package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class Start extends AppCompatActivity {

    //  All of this activity is explained on the first Java activity which is the Home activity

    public static final String[] languages = {"Select Language", "English", "Spanish", "Greek"};
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageButton btnHomeStart = findViewById(R.id.btnHomeStart);
        Spinner spinner = findViewById(R.id.spinner3);
        ImageView imgLang = findViewById(R.id.imgLang3);
        TextView textStart = findViewById(R.id.textStart);

        webView = findViewById(R.id.webView);

//      Here we apply some settings and configurations to the WebView, a WebView is a "window" to
//      load URL, so for this app, first we made H5P project and we saved it as a HTML file and
//      we transfered it locally to the Android project and we load it on the WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new Callback());
        webView.loadUrl("file:///android_asset/index.html");

        btnHomeStart.setOnClickListener(v -> {
            Intent i = new Intent(Start.this, Home.class);
            startActivity(i);
        });

        spinner.setVisibility(View.INVISIBLE);
        imgLang.setVisibility(View.INVISIBLE);

        if (textStart.getText().equals("Get Started")) {
            imgLang.setImageResource(R.drawable.usa);
        } else if (textStart.getText().equals("Empezemos")) {
            imgLang.setImageResource(R.drawable.spain);
        } else if (textStart.getText().equals("Ξεκίνα")) {
            imgLang.setImageResource(R.drawable.greece);
        }

        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, languages);
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adap);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                if (selected.equals("English")) {
                    setLocale( "en", Start.this);
                    finish();
                    startActivity(getIntent());
                } else if (selected.equals("Spanish")) {
                    setLocale( "es", Start.this);
                    finish();
                    startActivity(getIntent());
                } else if (selected.equals("Greek")) {
                    setLocale( "el", Start.this);
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


//  This method is for the WebView to  continue loading the URL as usual without any special
//  handling
    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.btnLogo:
                Intent i = new Intent(Start.this, AboutUs.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setLocale(String langCode, Activity act) {
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources res = act.getResources();
        Configuration con = res.getConfiguration();
        con.setLocale(locale);
        res.updateConfiguration(con, res.getDisplayMetrics());
    }
}