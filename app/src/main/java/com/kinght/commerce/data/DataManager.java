package com.kinght.commerce.data;


import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Servers.Servers;

import java.util.List;

public interface DataManager  {
    void saveAuthorizationKey(String authorizationKey);
    String getAuthorizationKey();

    void registerStepOne(String name, String surname, String password, String serverId, String nickname, String phoneNumber, boolean isShowPhoneNumber, String pnsToken, ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void registerStepTwo(String smsCode,ServiceCallback<CommonResponse> commonResponseServiceCallback);

    void getServers(ServiceCallback<List<Servers>> serviceCallback);
}
