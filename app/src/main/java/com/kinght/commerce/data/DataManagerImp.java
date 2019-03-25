package com.kinght.commerce.data;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kinght.commerce.data.network.ApiServices;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.AuthorizationResponse;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Entries.UpdateEntryRequest;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.data.network.entities.Event.Event;
import com.kinght.commerce.data.network.entities.Event.Events;
import com.kinght.commerce.data.network.entities.Event.Hour;
import com.kinght.commerce.data.network.entities.ForgetPasswordRequest;
import com.kinght.commerce.data.network.entities.LoginRequest;
import com.kinght.commerce.data.network.entities.Lottery.Lottery;
import com.kinght.commerce.data.network.entities.Notification.Notifications;
import com.kinght.commerce.data.network.entities.Promotion.Promotions;
import com.kinght.commerce.data.network.entities.RegisterObject;
import com.kinght.commerce.data.network.entities.Report.ReportRequest;
import com.kinght.commerce.data.network.entities.Servers.CreateEntryRequest;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.data.network.entities.Settings.Settings;
import com.kinght.commerce.data.pref.PrefHelper;
import com.kinght.commerce.utility.Constant;

import java.util.ArrayList;
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
    public void startApplication(String pnsToken, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        apiServices.startApplication(pnsToken,new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {
                commonResponseServiceCallback.onSuccess(response);
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {

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

        apiServices.registerStepOne(registerObject, new ServiceCallback<AuthorizationResponse>() {


            @Override
            public void onSuccess(AuthorizationResponse response) {
                prefHelper.saveAuthorizationKey(response.getSecretKey());
                Constant.authorizationKey=response.getSecretKey();
                commonResponseServiceCallback.onSuccess(new CommonResponse());

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
                prefHelper.saveAuthorizationKey(response.getSecretKey());
                Constant.authorizationKey=response.getSecretKey();
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
        Constant.authorizationKey=prefHelper.getAuthorizationKey();
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

    @Override
    public void getNotifications(ServiceCallback<List<Notifications>> listServiceCallback) {
        apiServices.getNotifications(listServiceCallback);
    }

    @Override
    public void forgetPasswordStepOne(String phoneNumber, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        apiServices.forgetPasswordStepOne(phoneNumber,commonResponseServiceCallback);
    }

    @Override
    public void forgetPasswordStepTwo(String phoneNumber, String smsCode, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        apiServices.forgetPasswordStepTwo(phoneNumber,smsCode,commonResponseServiceCallback);
    }

    @Override
    public void forgetPasswordStepThree(String phoneNumber, String password, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        ForgetPasswordRequest forgetPasswordRequest=new ForgetPasswordRequest();
        forgetPasswordRequest.setPassword(password);

        apiServices.forgetPasswordStepThree(phoneNumber,forgetPasswordRequest,commonResponseServiceCallback);

    }

    @Override
    public void updateProfile(String nickname, String name, String surname, String registerServerId, boolean isShowPassword, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        User user=new User();
        user.setNickname(nickname);
        user.setName(name);
        user.setSurname(surname);
        Servers servers=new Servers();
        servers.set_id(registerServerId);
        user.setServers(servers);
        user.setIsShowPhoneNumber(isShowPassword);

        apiServices.updateMe(user,commonResponseServiceCallback);
    }

    @Override
    public void getEvents(ServiceCallback<List<Event>> listServiceCallback) {
        final int[] id = {0};

        List<Event> eventList=new ArrayList<>();

        if(prefHelper.getEventListCache() != ""){
            eventList=new Gson().fromJson(prefHelper.getEventListCache(),new TypeToken<List<Event>>(){}.getType());
            listServiceCallback.onSuccess(eventList);

        }else {
            List<Event> finalEventList = eventList;
            apiServices.getEvents(new ServiceCallback<List<Events>>() {
                @Override
                public void onSuccess(List<Events> response) {
                    for(Events events:response){
                        Event event=new Event();
                        event.setEventName(events.getEventName());
                        event.setEventDays(events.getEventDays());
                        List<Hour> hours=new ArrayList<>();
                        for(String hour:events.getEventHours()){
                            Hour hour1=new Hour();
                            hour1.setHour(hour);
                            hour1.setId(id[0]);
                            id[0]++;
                            hours.add(hour1);
                        }
                        event.setEventHours(hours);
                        finalEventList.add(event);
                    }
                    updateEventListCache(finalEventList);
                    listServiceCallback.onSuccess(finalEventList);
                }

                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(int code, String errorResponse) {

                }
            });
        }


    }

    @Override
    public void updateEventListCache(List<Event> eventList) {
        String data=new Gson().toJson(eventList);
        prefHelper.saveEventListCache(data);
    }

    @Override
    public Hour getEventHours(int id) {
        List<Event> eventList=new ArrayList<>();

        if(prefHelper.getEventListCache() != ""){
            eventList=new Gson().fromJson(prefHelper.getEventListCache(),new TypeToken<List<Event>>(){}.getType());
            for(Event event:eventList){
                for(Hour hour:event.getEventHours()){
                    if(id==hour.getId()){
                        return hour;
                    }
                }
            }

        }

        return null;
    }

    @Override
    public void removeCache() {
        prefHelper.saveEventListCache("");
        prefHelper.saveAuthorizationKey("");
    }

    @Override
    public void getSettings(ServiceCallback<List<Settings>> listServiceCallback) {
        apiServices.getSettings(listServiceCallback);
    }

    @Override
    public void updateSettings(List<Settings> serversList, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        apiServices.updateSettings(serversList,commonResponseServiceCallback);
    }


}
