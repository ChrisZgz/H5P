package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.fondo);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageButton button = findViewById(R.id.btn);
        ImageButton buttonTwo = findViewById(R.id.btnTwo);

        button.setOnClickListener(v -> {
            Intent i = new Intent(Splash.this, MainActivity.class);
            startActivity(i);
        });

        buttonTwo.setOnClickListener(V -> {
            Intent i = new Intent(Splash.this, MainActivity2.class);
            startActivity(i);
        });

      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
            }
        }, 3000);*/
    }
}