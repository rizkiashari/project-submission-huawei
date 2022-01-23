package com.rizkiashari.restoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.rizkiashari.restoapp.model.RvModel;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ArrayList<RvModel> item = new ArrayList<>();
        item.add(new RvModel(R.drawable.briyani, "Mas Dar", "Nasi Briyani"));
        item.add(new RvModel(R.drawable.briyani, "Handi", "Sauce Tonkatsu"));
        item.add(new RvModel(R.drawable.briyani, "The Curry", "Chicken Katsu"));

        recyclerView = findViewById(R.id.recview1);
        staticRvAdapter = new StaticRvAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);

    }

//    Add New Resto
    public void addResto(View view){
        Intent intent = new Intent(Home.this, AddRestoActivity.class);

        startActivity(intent);
    }
}