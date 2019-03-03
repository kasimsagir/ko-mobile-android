package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ApiClient;
import com.kinght.commerce.data.network.ApiInterface;
import com.kinght.commerce.data.network.NetworkError;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.AuthorizationResponse;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.ForgetPasswordRequest;
import com.kinght.commerce.data.network.entities.LoginRequest;
import com.kinght.commerce.data.network.entities.RegisterObject;
import com.kinght.commerce.utility.CommonUtils;
import com.kinght.commerce.utility.Constant;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserServicesImp implements UserServices {

    ApiInterface apiInterface;
    @Inject
    public UserServicesImp(ApiClient apiClient){
        apiInterface=apiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void registerStepOne(RegisterObject registerObject, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        Call<CommonResponse> call=apiInterface.registerStepOne(registerObject);

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if(response.isSuccessful()){
                    commonResponseServiceCallback.onSuccess(response.body());
                }else {
                    try {
                        commonResponseServiceCallback.onError(response.code(),CommonUtils.errorHandler(response.errorBody().string()).getMessage());
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
    public void registerStepTwo(String smsCode, ServiceCallback<AuthorizationResponse> authorizationResponseServiceCallback) {

        Call<AuthorizationResponse> call=apiInterface.registerStepTwo(smsCode);

        call.enqueue(new Callback<AuthorizationResponse>() {
            @Override
            public void onResponse(Call<AuthorizationResponse> call, Response<AuthorizationResponse> response) {
                if(response.isSuccessful()){
                    authorizationResponseServiceCallback.onSuccess(response.body());
                }else {
                    try {
                        authorizationResponseServiceCallback.onError(response.code(),CommonUtils.errorHandler(response.errorBody().string()).getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthorizationResponse> call, Throwable t) {
                authorizationResponseServiceCallback.onError(Constant.ERROR_CODE,new NetworkError(t).response());
            }
        });
    }

    @Override
    public void forgetPasswordStepOne(String phoneNumber, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        Call<CommonResponse> call=apiInterface.forgetPasswordStepOne(phoneNumber);

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if(response.isSuccessful()){
                    commonResponseServiceCallback.onSuccess(response.body());
                }else {
                    try {
                        commonResponseServiceCallback.onError(response.code(),CommonUtils.errorHandler(response.errorBody().string()).getMessage());
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
    public void forgetPasswordStepTwo(String phoneNumber, String smsCode, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        Call<CommonResponse> call=apiInterface.forgetPasswordStepTwo(phoneNumber,smsCode);

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if(response.isSuccessful()){
                    commonResponseServiceCallback.onSuccess(response.body());
                }else {
                    try {
                        commonResponseServiceCallback.onError(response.code(),CommonUtils.errorHandler(response.errorBody().string()).getMessage());
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
    public void forgetPasswordStepThree(String phoneNumber, ForgetPasswordRequest forgetPasswordRequest, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        Call<CommonResponse> call=apiInterface.forgetPasswordStepThree(phoneNumber,forgetPasswordRequest);

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if(response.isSuccessful()){
                    commonResponseServiceCallback.onSuccess(response.body());
                }else {
                    try {
                        commonResponseServiceCallback.onError(response.code(),CommonUtils.errorHandler(response.errorBody().string()).getMessage());
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
    public void login(LoginRequest loginRequest, ServiceCallback<AuthorizationResponse> authorizationResponseServiceCallback) {
        Call<AuthorizationResponse> call=apiInterface.login(loginRequest);

        call.enqueue(new Callback<AuthorizationResponse>() {
            @Override
            public void onResponse(Call<AuthorizationResponse> call, Response<AuthorizationResponse> response) {
                if(response.isSuccessful()){
                    authorizationResponseServiceCallback.onSuccess(response.body());
                }else {
                    try {
                        authorizationResponseServiceCallback.onError(response.code(),CommonUtils.errorHandler(response.errorBody().string()).getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthorizationResponse> call, Throwable t) {
                authorizationResponseServiceCallback.onError(Constant.ERROR_CODE,new NetworkError(t).response());
            }
        });
    }
}
