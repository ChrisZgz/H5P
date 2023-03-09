package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class Home extends AppCompatActivity {

    public static final String[] languages = {"Select Language", "English", "Spanish", "Greek"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.fondo);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnAbout = findViewById(R.id.btnAbout);
        Button btnStart = findViewById(R.id.btnStart);
        TextView textWelcome = findViewById(R.id.textWelcome);
        ImageView imgLang = findViewById(R.id.imgLang);
        Spinner spinner = findViewById(R.id.spinner);

        if (textWelcome.getText().equals("CEM4SME Minigame")) {
            imgLang.setImageResource(R.drawable.usa);
        } else if (textWelcome.getText().equals("Minijuego de CEM4SME")) {
            imgLang.setImageResource(R.drawable.spain);
        } else if (textWelcome.getText().equals("Μίνι παιχνίδι CEM4SME")) {
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
                    setLocale( "en", Home.this);
                    finish();
                    startActivity(getIntent());
                } else if (selected.equals("Spanish")) {
                    setLocale( "es", Home.this);
                    finish();
                    startActivity(getIntent());
                } else if (selected.equals("Greek")) {
                    setLocale( "el", Home.this);
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAbout.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, AboutUs.class);
            startActivity(i);
        });

        btnStart.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, Start.class);
            startActivity(i);
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