package com.rizkiashari.restoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class DetailActivity extends AppCompatActivity {

    private String nameResto="",openDate = "",
            picture="", location="", address="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);




    }
}