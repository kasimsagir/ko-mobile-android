package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ApiClient;
import com.kinght.commerce.data.network.ApiInterface;
import com.kinght.commerce.data.network.NetworkError;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.Servers.ServerResponse;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.utility.CommonUtils;
import com.kinght.commerce.utility.Constant;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServerServicesImp implements ServerServices {

    ApiInterface apiInterface;
    @Inject
    public ServerServicesImp(ApiClient apiClient){
        apiInterface=apiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void getServers(ServiceCallback<List<Servers>> serversServiceCallback) {
        Call<ServerResponse> call=apiInterface.getServers();

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()){
                    serversServiceCallback.onSuccess(response.body().getServers());
                }else {
                    try {
                        serversServiceCallback.onError(Constant.ERROR_CODE, CommonUtils.errorHandler(response.errorBody().string()).getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                serversServiceCallback.onError(Constant.ERROR_CODE,new NetworkError(t).response());
            }
        });
    }
}
