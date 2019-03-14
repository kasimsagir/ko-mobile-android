package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ApiClient;
import com.kinght.commerce.data.network.ApiInterface;
import com.kinght.commerce.data.network.NetworkError;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Lottery.Lottery;
import com.kinght.commerce.data.network.entities.Lottery.LotteryResponse;
import com.kinght.commerce.utility.CommonUtils;
import com.kinght.commerce.utility.Constant;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LotteryServicesImp implements LotteryServices {

    ApiInterface apiInterface;

    @Inject
    public LotteryServicesImp(ApiClient apiClient) {
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void getLotteries(ServiceCallback<List<Lottery>> listServiceCallback) {
        Call<LotteryResponse> call=apiInterface.getLotteries();

        call.enqueue(new Callback<LotteryResponse>() {
            @Override
            public void onResponse(Call<LotteryResponse> call, Response<LotteryResponse> response) {
                if(response.isSuccessful()){
                    listServiceCallback.onSuccess(response.body().getLotteries());
                }else {
                    try {
                        listServiceCallback.onError(response.code(), CommonUtils.errorHandler(response.errorBody().string()).getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LotteryResponse> call, Throwable t) {
                listServiceCallback.onError(Constant.ERROR_CODE,new NetworkError(t).response());
            }
        });
    }

    @Override
    public void getLotteryDetail(String lotteryId, ServiceCallback<Lottery> lotteryServiceCallback) {
        Call<LotteryResponse> call=apiInterface.getLotteryDetail(lotteryId);

        call.enqueue(new Callback<LotteryResponse>() {
            @Override
            public void onResponse(Call<LotteryResponse> call, Response<LotteryResponse> response) {
                if(response.isSuccessful()){
                    lotteryServiceCallback.onSuccess(response.body().getLottery());
                }else {
                    try {
                        lotteryServiceCallback.onError(response.code(), CommonUtils.errorHandler(response.errorBody().string()).getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LotteryResponse> call, Throwable t) {
                lotteryServiceCallback.onError(Constant.ERROR_CODE,new NetworkError(t).response());
            }
        });
    }

    @Override
    public void partipicateLottery(String lotteryId, ServiceCallback<CommonResponse> lotteryServiceCallback) {
        Call<CommonResponse> call=apiInterface.participateLottery(lotteryId);

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if(response.isSuccessful()){
                    lotteryServiceCallback.onSuccess(response.body());
                }else {
                    try {
                        lotteryServiceCallback.onError(response.code(), CommonUtils.errorHandler(response.errorBody().string()).getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                lotteryServiceCallback.onError(Constant.ERROR_CODE,new NetworkError(t).response());
            }
        });
    }
}
