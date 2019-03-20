package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.AuthorizationResponse;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.data.network.entities.ForgetPasswordRequest;
import com.kinght.commerce.data.network.entities.LoginRequest;
import com.kinght.commerce.data.network.entities.RegisterObject;

public interface UserServices {

    void registerStepOne(RegisterObject registerObject, ServiceCallback<AuthorizationResponse> commonResponseServiceCallback);
    void registerStepTwo(String smsCode, ServiceCallback<AuthorizationResponse> authorizationResponseServiceCallback);
    void forgetPasswordStepOne(String phoneNumber,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void forgetPasswordStepTwo(String phoneNumber,String smsCode,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void forgetPasswordStepThree(String phoneNumber, ForgetPasswordRequest forgetPasswordRequest,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void login(LoginRequest loginRequest,ServiceCallback<AuthorizationResponse> authorizationResponseServiceCallback);
    void getUser(String userId, ServiceCallback<User> userServiceCallback);
    void getMe(ServiceCallback<User> userServiceCallback);
    void updateMe(User user,ServiceCallback<CommonResponse> commonResponseServiceCallback);
}
