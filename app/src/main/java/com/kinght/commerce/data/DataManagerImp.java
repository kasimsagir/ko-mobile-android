package com.kinght.commerce.data;



import com.kinght.commerce.data.network.ApiServices;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.AuthorizationResponse;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.RegisterObject;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.data.pref.PrefHelper;

import java.util.List;

import javax.inject.Inject;

public class DataManagerImp implements DataManager{
    private ApiServices apiServices;
    private PrefHelper prefHelper;


    @Inject
    public DataManagerImp(ApiServices apiServices,PrefHelper prefHelper){
        this.apiServices=apiServices;
        this.prefHelper=prefHelper;
    }


    @Override
    public void saveAuthorizationKey(String authKey) {
        prefHelper.saveAuthorizationKey(authKey);
    }

    @Override
    public String getAuthorizationKey() {
        return prefHelper.getAuthorizationKey();
    }

    @Override
    public void registerStepOne(String name, String surname, String password, String serverId, String nickname, String phoneNumber, boolean isShowPhoneNumber, String pnsToken, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        RegisterObject registerObject=new RegisterObject();
        registerObject.setName(name);
        registerObject.setSurname(surname);
        registerObject.setPassword(password);
        registerObject.setServerId(serverId);
        registerObject.setPhoneNumber(phoneNumber);
        registerObject.setIsShowPhoneNumber(isShowPhoneNumber);
        registerObject.setPnsToken(pnsToken);

        apiServices.registerStepOne(registerObject, new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {
                commonResponseServiceCallback.onSuccess(response);
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {
                commonResponseServiceCallback.onError(code,errorResponse);
            }
        });

    }

    @Override
    public void registerStepTwo(String smsCode, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
            apiServices.registerStepTwo(smsCode, new ServiceCallback<AuthorizationResponse>() {
                @Override
                public void onSuccess(AuthorizationResponse response) {
                    commonResponseServiceCallback.onSuccess();
                }

                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(int code, String errorResponse) {
                    commonResponseServiceCallback.onError(code,errorResponse);
                }
            });
    }

    @Override
    public void getServers(ServiceCallback<List<Servers>> serviceCallback) {
        apiServices.getServers(new ServiceCallback<List<Servers>>() {
            @Override
            public void onSuccess(List<Servers> response) {
                serviceCallback.onSuccess(response);
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {
                serviceCallback.onError(code,errorResponse);
            }
        });
    }


}
