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

//    This is the Java class related to the first screen, which in this case would be the Home
//    screen.

//    First we have a list of Arrays with the values we want to put in the spinner;
//    the languages that we want in the application
    public static final String[] languages = {"Select Language", "English", "Spanish", "Greek"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//      Here we put the background to the layout, and the other 2 lines are to relate the Java act-
//      ivity, because each layout have a Java activity with all the code to control that layout
        setTheme(R.style.background);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//      Then we identify the objects on the layout such as the buttons, the labels (textview),
//      the imageview and the spinner
        Button btnAbout = findViewById(R.id.btnAbout);
        Button btnStart = findViewById(R.id.btnStart);
        TextView textWelcome = findViewById(R.id.textWelcome);
        ImageView imgLang = findViewById(R.id.imgLang);
        Spinner spinner = findViewById(R.id.spinner);

//      With this statement we change the ImageView depending on which language we choose
        if (textWelcome.getText().equals("CEM4SME Minigame")) {
            imgLang.setImageResource(R.drawable.usa);
        } else if (textWelcome.getText().equals("Minijuego de CEM4SME")) {
            imgLang.setImageResource(R.drawable.spain);
        } else if (textWelcome.getText().equals("Μίνι παιχνίδι CEM4SME")) {
            imgLang.setImageResource(R.drawable.greece);
        }


//      Here we prepare the spinner and we put the list that we created at first
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, languages);
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adap);
        spinner.setSelection(0);

//      Here we change the language of the application depending on which value of the Spinner we
//      choose
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

//      All the buttons have to make an action when they are pressed, so we use setOnClickListener
//      in this action the action would be to create an Intent which is the way to go from a Screen
//      to another, but we have to start it, because if we leave it as it is, it won't do nothing.
        btnAbout.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, AboutUs.class);
            startActivity(i);
        });

        btnStart.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, Start.class);
            startActivity(i);
        });

    }

    //  Here we create the upper menu with a button with an info logo, to go to the AboutUs screen.
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

//  This is the method we use to change the language of the application.
//  First of all, Locale is the language that we want for the application to be displayed, and all
//  the locales uses a code for example "en" for English, "es" for Spanish, that's why we need a
//  String, to have the locale code, so with the locale we can change the language of the app.
    private void setLocale(String langCode, Activity act) {
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources res = act.getResources();
        Configuration con = res.getConfiguration();
        con.setLocale(locale);
        res.updateConfiguration(con, res.getDisplayMetrics());
    }
}