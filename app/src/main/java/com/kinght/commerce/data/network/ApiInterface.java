package com.kinght.commerce.data.network;


import com.kinght.commerce.data.network.entities.AuthorizationResponse;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.ForgetPasswordRequest;
import com.kinght.commerce.data.network.entities.LoginRequest;
import com.kinght.commerce.data.network.entities.RegisterObject;
import com.kinght.commerce.data.network.entities.Servers.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {



    @GET("secure/start-application")
    Call<CommonResponse> startApplication();

    @POST("login")
    Call<AuthorizationResponse> login(@Body LoginRequest loginRequest);

    @Headers("Cache-Control: no-store")
    @POST("register")
    Call<CommonResponse> registerStepOne(@Body RegisterObject registerObject);

    @Headers("Cache-Control: no-store")
    @GET("register")
    Call<AuthorizationResponse> registerStepTwo(@Query("smsCode") String smsCode);

    @GET("forget-password-step-one")
    Call<CommonResponse> forgetPasswordStepOne(@Query("phoneNumber") String phoneNumber);

    @GET("forget-password-step-two")
    Call<CommonResponse> forgetPasswordStepTwo(@Query("phoneNumber") String phoneNumber, @Query("smsCode") String smsCode);

    @PATCH("forget-password-step-three")
    Call<CommonResponse> forgetPasswordStepThree(@Query("phoneNumber") String phoneNumber, @Body ForgetPasswordRequest forgetPasswordRequest);

//    @Headers("Cache-Control: public, max-stale=604800")
    @GET("servers")
    Call<ServerResponse> getServers();

}
