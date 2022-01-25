package com.rizkiashari.restoapp2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rizkiashari.restoapp.API.APIRequestData;
import com.rizkiashari.restoapp.API.RetroServer;
import com.rizkiashari.restoapp.Adapter.AdapterMenu;
import com.rizkiashari.restoapp.databinding.ActivityDetailBinding;
import com.rizkiashari.restoapp.model.DataItem;
import com.rizkiashari.restoapp.model.Response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private ArrayList<DataItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvListMenu.setHasFixedSize(true);

        retrieveFood();

    }

    private void showRecyclerView() {
        AdapterMenu adapter = new AdapterMenu(list);
        binding.rvListMenu.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListMenu.setAdapter(adapter);
    }

    public void retrieveFood(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<Response> tampilData = ardData.ardRetrieveFood();

        tampilData.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                String status = response.body().getStatus();
                String message = response.body().getMessage();

                Toast.makeText(DetailActivity.this,"Status: "+status +" | message "
                                + message,
                        Toast.LENGTH_SHORT).show();
                list = new ArrayList<>();
                list = (ArrayList<DataItem>) response.body().getData();

                showRecyclerView();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}