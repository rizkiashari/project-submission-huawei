package com.rizkiashari.restoapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
<<<<<<< HEAD:app/src/main/java/com/rizkiashari/restoapp2/Home.java
=======
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
>>>>>>> 9aea9a08a8c13283c457c4647b725b6dead236ea:app/src/main/java/com/rizkiashari/restoapp/Home.java
import android.widget.Toast;


import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.banner.BannerView;
import com.rizkiashari.restoapp2.API.APIRequestData;
import com.rizkiashari.restoapp2.API.RetroServer;
import com.rizkiashari.restoapp2.Adapter.AdapterData;
import com.rizkiashari.restoapp2.model.DataModel;
import com.rizkiashari.restoapp2.model.ResponModel;
import com.rizkiashari.restoapp2.model.RvModel;

import com.huawei.hms.ads.HwAds;

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

    private static String adID ="testw6vs28auh3";
    private static String reward_ad_id ="testx9dtjwj8hp";
    private ConstraintLayout constLytBanner;

    private BannerView bannerView;
    private static final int REFRESH_TIME = 30;


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
//        getDetail(lmData.get);
    }

    public void getDetail(int id){
        APIRequestData getDetails = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponModel> detail = getDetails.getDetails(id);


        detail.enqueue(new Callback<ResponModel>() {
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

        ImageView img = findViewById(R.id.imageView2);
        img.setOnClickListener(e -> {
            Intent a = new Intent(Home.this, DetailActivity.class);
            startActivity(a);
        });

        HwAds.init(this);

        bannerView = findViewById(R.id.hw_banner_view);
        bannerView.setBannerRefresh(REFRESH_TIME);
        AdParam adParam = new AdParam.Builder().build();
        bannerView.loadAd(adParam);


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