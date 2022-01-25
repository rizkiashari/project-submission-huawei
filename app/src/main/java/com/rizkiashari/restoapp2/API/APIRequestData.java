package com.rizkiashari.restoapp2.API;

import com.rizkiashari.restoapp2.model.ResponModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {

    @GET("resto")
    Call<ResponModel> ardRetrieveData();

}
