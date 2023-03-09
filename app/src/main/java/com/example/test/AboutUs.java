package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class AboutUs extends AppCompatActivity {

    public static final String[] languages = {"Select Language", "English", "Spanish", "Greek"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ImageView imgLang = findViewById(R.id.imgLang2);
        Spinner spinner = findViewById(R.id.spinner2);
        TextView textAbout = findViewById(R.id.textAbout);

        spinner.setVisibility(View.INVISIBLE);
        imgLang.setVisibility(View.INVISIBLE);

        if (textAbout.getText().equals("About Us")) {
            imgLang.setImageResource(R.drawable.usa);
        } else if (textAbout.getText().equals("Sobre Nosotros")) {
            imgLang.setImageResource(R.drawable.spain);
        } else if (textAbout.getText().equals("σχετικά με εμάς")) {
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
                    setLocale( "en", AboutUs.this);
                    finish();
                    startActivity(getIntent());
                } else if (selected.equals("Spanish")) {
                    setLocale( "es", AboutUs.this);
                    finish();
                    startActivity(getIntent());
                } else if (selected.equals("Greek")) {
                    setLocale( "el", AboutUs.this);
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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