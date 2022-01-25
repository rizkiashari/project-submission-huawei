package com.rizkiashari.restoapp2.API;

<<<<<<< HEAD:app/src/main/java/com/rizkiashari/restoapp2/API/APIRequestData.java
import com.rizkiashari.restoapp2.model.ResponModel;
=======
import com.rizkiashari.restoapp.model.ResponModel;
import com.rizkiashari.restoapp.model.Response;
>>>>>>> 9aea9a08a8c13283c457c4647b725b6dead236ea:app/src/main/java/com/rizkiashari/restoapp/API/APIRequestData.java

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIRequestData {

    @GET("resto")
    Call<ResponModel> ardRetrieveData();

    @GET("resto/{id}")
    Call<ResponModel> getDetails(@Path("id") int restoId);

    @GET("typefood")
    Call<Response> ardRetrieveFood();

}
