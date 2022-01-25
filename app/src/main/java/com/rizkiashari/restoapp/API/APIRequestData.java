package com.rizkiashari.restoapp.API;

import com.rizkiashari.restoapp.model.ResponModel;
import com.rizkiashari.restoapp.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {

    @GET("resto")
    Call<ResponModel> ardRetrieveData();

    @GET("typefood")
    Call<Response> ardRetrieveFood();

}
