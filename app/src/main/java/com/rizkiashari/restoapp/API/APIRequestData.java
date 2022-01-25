package com.rizkiashari.restoapp.API;

import com.rizkiashari.restoapp.model.ResponModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIRequestData {

    @GET("resto")
    Call<ResponModel> ardRetrieveData();

    @GET("resto/{id}")
    Call<ResponModel> getDetails(@Path("id") int restoId);
}
