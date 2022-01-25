package com.rizkiashari.restoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rizkiashari.restoapp.API.APIRequestData;
import com.rizkiashari.restoapp.API.RetroServer;
import com.rizkiashari.restoapp.Adapter.AdapterData;
import com.rizkiashari.restoapp.model.DataModel;
import com.rizkiashari.restoapp.model.ResponModel;
import com.rizkiashari.restoapp.model.RvModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    private RecyclerView recyclerView, rvData;
    private StaticRvAdapter staticRvAdapter;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listFood = new ArrayList<>();


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

        rvData = findViewById(R.id.rvData);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        rvData.setLayoutManager(lmData);

        retrieveData();

        ImageView img = findViewById(R.id.imageView2);
        img.setOnClickListener(e -> {
//            Intent a = new Intent(this,DetailActivity.class);
//            startActivity(a);
        });

    }

    public void retrieveData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponModel> tampilData = ardData.ardRetrieveData();

        tampilData.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                String status = response.body().getStatus();
                String message = response.body().getMessage();

                Toast.makeText(Home.this,"Status: "+status +" | message "
                        + message,
                        Toast.LENGTH_SHORT).show();
                listFood = response.body().getData();

                adData = new AdapterData(Home.this, listFood);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {
                Toast.makeText(Home.this, "Server Error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




    //Search

}