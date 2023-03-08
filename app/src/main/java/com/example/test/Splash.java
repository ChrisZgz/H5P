package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Locale;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.fondo);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageButton button = findViewById(R.id.btn);
        ImageButton buttonTwo = findViewById(R.id.btnTwo);
        Button btnLanguage = findViewById(R.id.btnLanguage);

        button.setOnClickListener(v -> {
            Intent i = new Intent(Splash.this, MainActivity.class);
            startActivity(i);
        });

        buttonTwo.setOnClickListener(V -> {
            Intent i = new Intent(Splash.this, MainActivity2.class);
            startActivity(i);
        });

        btnLanguage.setOnClickListener(v -> {
            changeLanguage();
        });



      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
            }
        }, 3000);*/
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

    private void changeLanguage() {
        final String languages[] = {"English", "Spanish", "Greek"};
        AlertDialog.Builder lan = new AlertDialog.Builder(this);
        lan.setTitle("Choose a Language");
        lan.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (which == 0) {
                    setLocale("en");
                    recreate();
                } else if (which == 1) {
                    setLocale("es");
                    recreate();
                } else if (which == 1) {
                    setLocale("gr");
                    recreate();
                }
                lan.create();
                lan.show();
            }
        });
    }

    private void setLocale(String ur) {
        Locale locale = new Locale(ur);
        Locale.setDefault(locale);
        Configuration con = new Configuration();
        con.locale = locale;
        getBaseContext().getResources().updateConfiguration(con, getBaseContext().getResources()
                .getDisplayMetrics());

    }
}