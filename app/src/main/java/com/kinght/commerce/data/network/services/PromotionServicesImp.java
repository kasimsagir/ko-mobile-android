package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ApiClient;
import com.kinght.commerce.data.network.ApiInterface;
import com.kinght.commerce.data.network.NetworkError;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Promotion.PromotionResponse;
import com.kinght.commerce.data.network.entities.Promotion.Promotions;
import com.kinght.commerce.utility.CommonUtils;
import com.kinght.commerce.utility.Constant;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromotionServicesImp implements PromotionServices {

    ApiInterface apiInterface;

    @Inject
    public PromotionServicesImp(ApiClient apiClient) {
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void getPromotions(ServiceCallback<List<Promotions>> serviceCallback) {
        Call<PromotionResponse> call=apiInterface.getPromotionList();

        call.enqueue(new Callback<PromotionResponse>() {
            @Override
            public void onResponse(Call<PromotionResponse> call, Response<PromotionResponse> response) {
                if(response.isSuccessful()){
                    serviceCallback.onSuccess(response.body().getPromotions());
                }else {
                    try {
                        serviceCallback.onError(response.code(), CommonUtils.errorHandler(response.errorBody().string()).getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PromotionResponse> call, Throwable t) {
                serviceCallback.onError(Constant.ERROR_CODE,new NetworkError(t).response());
            }
        });
    }
}
