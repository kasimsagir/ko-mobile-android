package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ApiClient;
import com.kinght.commerce.data.network.ApiInterface;
import com.kinght.commerce.data.network.NetworkError;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.Notification.NotificationResponse;
import com.kinght.commerce.data.network.entities.Notification.Notifications;
import com.kinght.commerce.utility.CommonUtils;
import com.kinght.commerce.utility.Constant;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationServicesImp implements NotificationServices {
    ApiInterface apiInterface;

    @Inject
    public NotificationServicesImp(ApiClient apiClient) {
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }


    @Override
    public void getNotifications(ServiceCallback<List<Notifications>> notificationCallBack) {
        Call<NotificationResponse> call=apiInterface.getNotifications();

        call.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                if(response.isSuccessful()){
                    notificationCallBack.onSuccess(response.body().getNotifications());
                }else {
                    try {
                        notificationCallBack.onError(response.code(), CommonUtils.errorHandler(response.errorBody().string()).getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                notificationCallBack.onError(Constant.ERROR_CODE,new NetworkError(t).response());
            }
        });
    }
}
