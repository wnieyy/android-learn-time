package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView emailSplashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        emailSplashScreen = findViewById(R.id.emailSplash);

        Intent intent = getIntent();
        String emailSplash = intent.getStringExtra("Email");

        emailSplashScreen.setText(emailSplash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 3000);
    }
}