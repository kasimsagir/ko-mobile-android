package com.kinght.commerce.data;


import com.kinght.commerce.data.network.ApiServices;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.AuthorizationResponse;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Entries.UpdateEntryRequest;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.data.network.entities.LoginRequest;
import com.kinght.commerce.data.network.entities.Lottery.Lottery;
import com.kinght.commerce.data.network.entities.Promotion.Promotions;
import com.kinght.commerce.data.network.entities.RegisterObject;
import com.kinght.commerce.data.network.entities.Report.ReportRequest;
import com.kinght.commerce.data.network.entities.Servers.CreateEntryRequest;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.data.pref.PrefHelper;
import com.kinght.commerce.utility.Constant;

import java.util.List;

import javax.inject.Inject;

public class DataManagerImp implements DataManager {
    private ApiServices apiServices;
    private PrefHelper prefHelper;


    @Inject
    public DataManagerImp(ApiServices apiServices, PrefHelper prefHelper) {
        this.apiServices = apiServices;
        this.prefHelper = prefHelper;
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
    public void startApplication(ServiceCallback<Boolean> isRegister, ServiceCallback<Boolean> isHasUpdate) {
        apiServices.startApplication(new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {
                if (code == Constant.UNREGISTER_CODE) {
                    isRegister.onSuccess(false);
                } else {
                    isRegister.onSuccess(true);
                }
            }
        });
    }

    @Override
    public void registerStepOne(String name, String surname, String password, String serverId, String nickname, String phoneNumber, boolean isShowPhoneNumber, String pnsToken, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        RegisterObject registerObject = new RegisterObject();
        registerObject.setName(name);
        registerObject.setSurname(surname);
        registerObject.setPassword(password);
        registerObject.setServerId(serverId);
        registerObject.setPhoneNumber(phoneNumber);
        registerObject.setNickname(nickname);
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
                commonResponseServiceCallback.onError(code, errorResponse);
            }
        });

    }

    @Override
    public void registerStepTwo(String smsCode, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        apiServices.registerStepTwo(smsCode, new ServiceCallback<AuthorizationResponse>() {
            @Override
            public void onSuccess(AuthorizationResponse response) {
                prefHelper.saveAuthorizationKey(response.getSecretKey());
                commonResponseServiceCallback.onSuccess();
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {
                commonResponseServiceCallback.onError(code, errorResponse);
            }
        });
    }

    @Override
    public void login(String phoneNumber, String password, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setPhoneNumber(phoneNumber);
        loginRequest.setPassword(password);

        apiServices.login(loginRequest, new ServiceCallback<AuthorizationResponse>() {
            @Override
            public void onSuccess(AuthorizationResponse response) {
                commonResponseServiceCallback.onSuccess();
                saveAuthorizationKey(response.getSecretKey());
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
    public void getIntermediaries(ServiceCallback<User> userServiceCallback) {
        apiServices.getIntermediaries(userServiceCallback);
    }

    @Override
    public void getPromotions(ServiceCallback<List<Promotions>> listServiceCallback) {
        apiServices.getPromotions(listServiceCallback);
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
                serviceCallback.onError(code, errorResponse);
            }
        });
    }

    @Override
    public void createEntry(String serverId, String header, String message, int price, String base64Image, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        CreateEntryRequest createEntryRequest=new CreateEntryRequest();
        createEntryRequest.setHeader(header);
        createEntryRequest.setMessage(message);
        createEntryRequest.setPrice(price);
        createEntryRequest.setImageBase64(base64Image);

        apiServices.createEntry(serverId, createEntryRequest, new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {
                commonResponseServiceCallback.onSuccess(response);
            }

            @Override
            public void onSuccess() {
                commonResponseServiceCallback.onSuccess();

            }

            @Override
            public void onError(int code, String errorResponse) {
                commonResponseServiceCallback.onError(code,errorResponse);
            }
        });
    }

    @Override
    public void getServerEntries(String serverId, ServiceCallback<List<Entry>> listServiceCallback) {
        apiServices.getServerEntries(serverId,listServiceCallback);
    }

    @Override
    public void getUser(String userId, ServiceCallback<User> userServiceCallback) {
        apiServices.getUser(userId,userServiceCallback);
    }

    @Override
    public void createReport(String entryId, String header, String message, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        ReportRequest reportRequest=new ReportRequest();
        reportRequest.setHeader(header);
        reportRequest.setMessage(message);
        apiServices.createReport(entryId,reportRequest,commonResponseServiceCallback);
    }

    @Override
    public void getMe(ServiceCallback<User> userServiceCallback) {
        apiServices.getMe(userServiceCallback);
    }

    @Override
    public void getEntries(ServiceCallback<List<Entry>> listServiceCallback) {
        apiServices.getEntries(listServiceCallback);
    }

    @Override
    public void getLotteries(ServiceCallback<List<Lottery>> listServiceCallback) {
        apiServices.getLotteries(listServiceCallback);
    }

    @Override
    public void getLottery(String lotteryId, ServiceCallback<Lottery> lotteryServiceCallback) {
        apiServices.getLotteryDetail(lotteryId,lotteryServiceCallback);
    }

    @Override
    public void partipicateLottery(String lotteryId, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        apiServices.partipicateLottery(lotteryId,commonResponseServiceCallback);
    }

    @Override
    public void deleteEntry(String entryId, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        apiServices.deleteEntry(entryId,commonResponseServiceCallback);
    }

    @Override
    public void getEntry(String entryId, ServiceCallback<Entry> entryServiceCallback) {
        apiServices.getEntry(entryId,entryServiceCallback);
    }

    @Override
    public void updateEntryHeader(String header, String entryId, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        UpdateEntryRequest updateEntryRequest=new UpdateEntryRequest();
        updateEntryRequest.setHeader(header);
        apiServices.updateEntry("HEADER",entryId,updateEntryRequest,commonResponseServiceCallback);
    }

    @Override
    public void updateEntryMessage(String message, String entryId, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        UpdateEntryRequest updateEntryRequest=new UpdateEntryRequest();
        updateEntryRequest.setMessage(message);
        apiServices.updateEntry("MESSAGE",entryId,updateEntryRequest,commonResponseServiceCallback);
    }

    @Override
    public void updateEntryPrice(int price, String entryId, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        UpdateEntryRequest updateEntryRequest=new UpdateEntryRequest();
        updateEntryRequest.setPrice(price);
        apiServices.updateEntry("PRICE",entryId,updateEntryRequest,commonResponseServiceCallback);
    }

    @Override
    public void updateEntryServer(String serverId, String entryId, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        UpdateEntryRequest updateEntryRequest=new UpdateEntryRequest();
        updateEntryRequest.setServer(serverId);
        apiServices.updateEntry("SERVER",entryId,updateEntryRequest,commonResponseServiceCallback);
    }


}
