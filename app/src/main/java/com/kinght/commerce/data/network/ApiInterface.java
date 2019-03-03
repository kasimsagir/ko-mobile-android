package com.kinght.commerce.data.network;


import com.kinght.commerce.data.network.entities.AuthorizationResponse;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.RegisterObject;
import com.kinght.commerce.data.network.entities.Servers.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {




    @Headers("Cache-Control: no-store")
    @POST("register")
    Call<CommonResponse> registerStepOne(@Body RegisterObject registerObject);

    @Headers("Cache-Control: no-store")
    @GET("register")
    Call<AuthorizationResponse> registerStepTwo(@Query("smsCode") String smsCode);

//    @Headers("Cache-Control: public, max-stale=604800")
    @GET("servers")
    Call<ServerResponse> getServers();

}
