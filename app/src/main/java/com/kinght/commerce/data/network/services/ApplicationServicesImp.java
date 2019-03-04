package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ApiClient;
import com.kinght.commerce.data.network.ApiInterface;
import com.kinght.commerce.data.network.NetworkError;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.utility.CommonUtils;
import com.kinght.commerce.utility.Constant;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplicationServicesImp implements ApplicationServices {
    ApiInterface apiInterface;

    @Inject
    public ApplicationServicesImp(ApiClient apiClient) {
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void startApplication(ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        Call<CommonResponse> call=apiInterface.startApplication();

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if(response.isSuccessful()){
                    commonResponseServiceCallback.onSuccess(response.body());
                }else {
                    try {
                        commonResponseServiceCallback.onError(response.code(), CommonUtils.errorHandler(response.errorBody().string()).getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                commonResponseServiceCallback.onError(Constant.ERROR_CODE,new NetworkError(t).response());
            }
        });
    }

    @Override
    public void getIntermediaries(ServiceCallback<User> userServiceCallback) {
        Call<User> call=apiInterface.getIntermediaries();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    userServiceCallback.onSuccess(response.body());
                }else {
                    try {
                        userServiceCallback.onError(response.code(), CommonUtils.errorHandler(response.errorBody().string()).getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userServiceCallback.onError(Constant.ERROR_CODE,new NetworkError(t).response());
            }
        });
    }
}
