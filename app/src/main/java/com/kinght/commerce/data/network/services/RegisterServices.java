package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.AuthorizationResponse;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.RegisterObject;

public interface RegisterServices {

    void registerStepOne(RegisterObject registerObject, ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void registerStepTwo(String smsCode, ServiceCallback<AuthorizationResponse> authorizationResponseServiceCallback);
}
