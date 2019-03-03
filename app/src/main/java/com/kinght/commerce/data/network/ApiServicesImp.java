package com.kinght.commerce.data.network;



import com.kinght.commerce.data.network.entities.AuthorizationResponse;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.RegisterObject;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.data.network.services.RegisterServices;
import com.kinght.commerce.data.network.services.ServerServices;

import java.util.List;

import javax.inject.Inject;

public class ApiServicesImp implements ApiServices {

    RegisterServices registerServices;
    ServerServices serverServices;
    @Inject
    public ApiServicesImp(RegisterServices registerServices,ServerServices serverServices) {
        this.registerServices=registerServices;
        this.serverServices=serverServices;
    }


    @Override
    public void registerStepOne(RegisterObject registerObject, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        registerServices.registerStepOne(registerObject,commonResponseServiceCallback);
    }

    @Override
    public void registerStepTwo(String smsCode, ServiceCallback<AuthorizationResponse> authorizationResponseServiceCallback) {
        registerServices.registerStepTwo(smsCode,authorizationResponseServiceCallback);
    }

    @Override
    public void getServers(ServiceCallback<List<Servers>> serversServiceCallback) {
        serverServices.getServers(serversServiceCallback);
    }
}
