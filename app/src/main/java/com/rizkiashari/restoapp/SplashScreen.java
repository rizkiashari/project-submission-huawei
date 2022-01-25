package com.rizkiashari.restoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(()->{
            Intent signIntent = new Intent(SplashScreen.this, SignInAcrivity.class);
            startActivity(signIntent);
            finish();
        }, SPLASH_DISPLAY_LENGTH );
    }
}