package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class Splash extends AppCompatActivity {

    public static final String[] languages = {"Select Language", "English", "Spanish", "Greek"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.fondo);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView textWelcome = findViewById(R.id.textWelcome);
        ImageView imgLang = findViewById(R.id.imgLang);
        Spinner spinner = findViewById(R.id.spinner);
        ImageButton button = findViewById(R.id.btn);
        ImageButton buttonTwo = findViewById(R.id.btnTwo);

        imgLang.setImageResource(R.drawable.greece);

        if (textWelcome.getText().equals("Welcome to the App!")) {
            imgLang.setImageResource(R.drawable.usa);
        } else if (textWelcome.getText().equals("Bienvenido a la App!")) {
            imgLang.setImageResource(R.drawable.spain);
        } else if (textWelcome.getText().equals("καλώς ήρθατε στην εφαρμογή")) {
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
                    setLocale( "en", Splash.this);
                    finish();
                    startActivity(getIntent());
                } else if (selected.equals("Spanish")) {
                    setLocale( "es", Splash.this);
                    finish();
                    startActivity(getIntent());
                } else if (selected.equals("Greek")) {
                    setLocale( "el", Splash.this);
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(v -> {
            Intent i = new Intent(Splash.this, MainActivity.class);
            startActivity(i);
        });

        buttonTwo.setOnClickListener(V -> {
            Intent i = new Intent(Splash.this, MainActivity2.class);
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