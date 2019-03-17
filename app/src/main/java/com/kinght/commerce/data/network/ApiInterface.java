package com.kinght.commerce.data.network;


import com.kinght.commerce.data.network.entities.AuthorizationResponse;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Entries.EntryResponse;
import com.kinght.commerce.data.network.entities.Entries.UpdateEntryRequest;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.data.network.entities.ForgetPasswordRequest;
import com.kinght.commerce.data.network.entities.LoginRequest;
import com.kinght.commerce.data.network.entities.Lottery.LotteryResponse;
import com.kinght.commerce.data.network.entities.Notification.NotificationResponse;
import com.kinght.commerce.data.network.entities.Promotion.PromotionResponse;
import com.kinght.commerce.data.network.entities.RegisterObject;
import com.kinght.commerce.data.network.entities.Report.ReportRequest;
import com.kinght.commerce.data.network.entities.Servers.CreateEntryRequest;
import com.kinght.commerce.data.network.entities.Servers.ServerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("secure/start-application")
    Call<CommonResponse> startApplication();

    @Headers("Cache-Control: no-store")
    @POST("login")
    Call<AuthorizationResponse> login(@Body LoginRequest loginRequest);

    @Headers("Cache-Control: no-store")
    @POST("register")
    Call<AuthorizationResponse> registerStepOne(@Body RegisterObject registerObject);

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

    @GET("servers/{serverId}/entries")
    Call<EntryResponse> getServerEntries(@Path("serverId") String serverId);

    @POST("secure/servers/{serverId}/entries")
    Call<CommonResponse> createEntry(@Path("serverId") String serverId, @Body CreateEntryRequest createEntryRequest);

    @Headers("Cache-Control: public, max-stale=604800")
    @GET("secure/intermediaries")
    Call<User> getIntermediaries();

    @GET("secure/promotions")
    Call<PromotionResponse> getPromotionList();

    @GET("secure/users/{userId}/detail")
    Call<User> getUser(@Path("userId") String userId);

    @POST("secure/entries/{entryId}/report")
    Call<CommonResponse> createReport(@Path("entryId") String entryId, @Body ReportRequest reportRequest);

    @GET("secure/users/me")
    Call<User> getMe();

    @GET("entries")
    Call<EntryResponse> getEntries();

    @GET("secure/lotteries")
    Call<LotteryResponse> getLotteries();

    @GET("secure/lotteries/{lotteryId}")
    Call<LotteryResponse> getLotteryDetail(@Path("lotteryId") String lotteryId);

    @POST("secure/lotteries/{lotteryId}")
    Call<CommonResponse> participateLottery(@Path("lotteryId") String lotteryId);

    @PATCH("entries/{entryId}/delete")
    Call<CommonResponse> deleteEntry(@Path("entryId") String entryId);

    @GET("entries/{entryId}/detail")
    Call<Entry> getEntryDetail(@Path("entryId") String entryId);

    @PATCH("secure/entries/{entryId}/update")
    Call<CommonResponse> updateEntry(@Query("area") String area, @Path("entryId") String entryId, @Body UpdateEntryRequest updateEntryRequest);

    @GET("secure/notifications")
    Call<NotificationResponse> getNotifications();
}
