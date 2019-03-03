package com.kinght.commerce.data.network;


import com.kinght.commerce.data.network.entities.AuthorizationResponse;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.ForgetPasswordRequest;
import com.kinght.commerce.data.network.entities.LoginRequest;
import com.kinght.commerce.data.network.entities.RegisterObject;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.data.network.services.ApplicationServices;
import com.kinght.commerce.data.network.services.UserServices;
import com.kinght.commerce.data.network.services.ServerServices;

import java.util.List;

import javax.inject.Inject;

public class ApiServicesImp implements ApiServices {

    UserServices userServices;
    ServerServices serverServices;
    ApplicationServices applicationServices;

    @Inject
    public ApiServicesImp(UserServices userServices, ServerServices serverServices, ApplicationServices applicationServices) {
        this.userServices = userServices;
        this.serverServices = serverServices;
        this.applicationServices = applicationServices;
    }


    @Override
    public void registerStepOne(RegisterObject registerObject, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        userServices.registerStepOne(registerObject, commonResponseServiceCallback);
    }

    @Override
    public void registerStepTwo(String smsCode, ServiceCallback<AuthorizationResponse> authorizationResponseServiceCallback) {
        userServices.registerStepTwo(smsCode, authorizationResponseServiceCallback);
    }

    @Override
    public void forgetPasswordStepOne(String phoneNumber, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        userServices.forgetPasswordStepOne(phoneNumber, commonResponseServiceCallback);
    }

    @Override
    public void forgetPasswordStepTwo(String phoneNumber, String smsCode, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        userServices.forgetPasswordStepTwo(phoneNumber, smsCode, commonResponseServiceCallback);
    }

    @Override
    public void forgetPasswordStepThree(String phoneNumber, ForgetPasswordRequest forgetPasswordRequest, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        userServices.forgetPasswordStepThree(phoneNumber, forgetPasswordRequest, commonResponseServiceCallback);
    }

    @Override
    public void login(LoginRequest loginRequest, ServiceCallback<AuthorizationResponse> authorizationResponseServiceCallback) {
        userServices.login(loginRequest, authorizationResponseServiceCallback);
    }

    @Override
    public void getServers(ServiceCallback<List<Servers>> serversServiceCallback) {
        serverServices.getServers(serversServiceCallback);
    }

    @Override
    public void startApplication(ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        applicationServices.startApplication(commonResponseServiceCallback);
    }
}
