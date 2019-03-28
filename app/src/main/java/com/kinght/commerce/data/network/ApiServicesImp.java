package com.kinght.commerce.data.network;


import com.kinght.commerce.data.network.entities.AuthorizationResponse;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Entries.UpdateEntryRequest;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.data.network.entities.Event.Events;
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
import com.kinght.commerce.data.network.services.ApplicationServices;
import com.kinght.commerce.data.network.services.EntryServices;
import com.kinght.commerce.data.network.services.EventServices;
import com.kinght.commerce.data.network.services.LotteryServices;
import com.kinght.commerce.data.network.services.NotificationServices;
import com.kinght.commerce.data.network.services.PromotionServices;
import com.kinght.commerce.data.network.services.UserServices;
import com.kinght.commerce.data.network.services.ServerServices;

import java.util.List;

import javax.inject.Inject;

public class ApiServicesImp implements ApiServices {

    UserServices userServices;
    ServerServices serverServices;
    ApplicationServices applicationServices;
    PromotionServices promotionServices;
    EntryServices entryServices;
    LotteryServices lotteryServices;
    NotificationServices notificationServices;
    EventServices eventServices;

    @Inject
    public ApiServicesImp(UserServices userServices, ServerServices serverServices, ApplicationServices applicationServices, PromotionServices promotionServices, EntryServices entryServices, LotteryServices lotteryServices,NotificationServices notificationServices, EventServices eventServices) {
        this.userServices = userServices;
        this.serverServices = serverServices;
        this.applicationServices = applicationServices;
        this.promotionServices=promotionServices;
        this.entryServices=entryServices;
        this.lotteryServices=lotteryServices;
        this.notificationServices=notificationServices;
        this.eventServices=eventServices;
    }


    @Override
    public void registerStepOne(RegisterObject registerObject, ServiceCallback<AuthorizationResponse> commonResponseServiceCallback) {
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
    public void getUser(String userId, ServiceCallback<User> userServiceCallback) {
        userServices.getUser(userId,userServiceCallback);
    }

    @Override
    public void getMe(ServiceCallback<User> userServiceCallback) {
        userServices.getMe(userServiceCallback);

    }


    @Override
    public void updateMe(User user, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        userServices.updateMe(user,commonResponseServiceCallback);
    }

    @Override
    public void getSettings(ServiceCallback<List<Settings>> settingsServiceCallback) {
        userServices.getSettings(settingsServiceCallback);
    }

    @Override
    public void updateSettings(List<Settings> serviceCallback, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        userServices.updateSettings(serviceCallback,commonResponseServiceCallback);
    }

    @Override
    public void getServers(ServiceCallback<List<Servers>> serversServiceCallback) {
        serverServices.getServers(serversServiceCallback);
    }

    @Override
    public void createEntry(String serverId, CreateEntryRequest createEntryRequest, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        serverServices.createEntry(serverId,createEntryRequest,commonResponseServiceCallback);
    }

    @Override
    public void getServerEntries(String serverId, ServiceCallback<List<Entry>> listServiceCallback) {
        serverServices.getServerEntries(serverId,listServiceCallback);
    }

    @Override
    public void startApplication(String pnsToken,ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        applicationServices.startApplication(pnsToken,commonResponseServiceCallback);
    }

    @Override
    public void getIntermediaries(ServiceCallback<User> userServiceCallback) {
        applicationServices.getIntermediaries(userServiceCallback);
    }

    @Override
    public void getPromotions(ServiceCallback<List<Promotions>> serviceCallback) {
        promotionServices.getPromotions(serviceCallback);
    }

    @Override
    public void createReport(String entryId, ReportRequest reportRequest, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        entryServices.createReport(entryId,reportRequest,commonResponseServiceCallback);
    }

    @Override
    public void getEntries(ServiceCallback<List<Entry>> entries) {
        entryServices.getEntries(entries);
    }

    @Override
    public void deleteEntry(String entryId, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        entryServices.deleteEntry(entryId,commonResponseServiceCallback);
    }

    @Override
    public void getEntry(String entryId, ServiceCallback<Entry> entryServiceCallback) {
        entryServices.getEntry(entryId,entryServiceCallback);
    }

    @Override
    public void updateEntry(Entry entry, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        entryServices.updateEntry(entry,commonResponseServiceCallback);

    }

    @Override
    public void getLotteries(ServiceCallback<List<Lottery>> listServiceCallback) {
        lotteryServices.getLotteries(listServiceCallback);
    }

    @Override
    public void getLotteryDetail(String lotteryId, ServiceCallback<Lottery> lotteryServiceCallback) {
        lotteryServices.getLotteryDetail(lotteryId,lotteryServiceCallback);
    }

    @Override
    public void partipicateLottery(String lotteryId, ServiceCallback<CommonResponse> lotteryServiceCallback) {
        lotteryServices.partipicateLottery(lotteryId,lotteryServiceCallback);
    }

    @Override
    public void getNotifications(ServiceCallback<List<Notifications>> notificationCallBack) {
        notificationServices.getNotifications(notificationCallBack);
    }

    @Override
    public void getEvents(ServiceCallback<List<Events>> eventsServiceCallback) {
        eventServices.getEvents(eventsServiceCallback);
    }
}
