package com.kinght.commerce.data;


import android.app.Notification;

import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.data.network.entities.Lottery.Lottery;
import com.kinght.commerce.data.network.entities.Notification.Notifications;
import com.kinght.commerce.data.network.entities.Promotion.Promotions;
import com.kinght.commerce.data.network.entities.Servers.Servers;

import java.util.List;

public interface DataManager  {
    void saveAuthorizationKey(String authorizationKey);
    String getAuthorizationKey();

    void startApplication(ServiceCallback<Boolean> isRegister,ServiceCallback<Boolean> isHasUpdate);
    void registerStepOne(String name, String surname, String password, String serverId, String nickname, String phoneNumber, boolean isShowPhoneNumber, String pnsToken, ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void registerStepTwo(String smsCode,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void login(String phoneNumber,String password,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void getIntermediaries(ServiceCallback<User> userServiceCallback);
    void getPromotions(ServiceCallback<List<Promotions>> listServiceCallback);
    void getServers(ServiceCallback<List<Servers>> serviceCallback);
    void createEntry(String serverId,String header,String message,int price,String base64Image,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void getServerEntries(String serverId, ServiceCallback<List<Entry>> listServiceCallback);
    void getUser(String userId,ServiceCallback<User> userServiceCallback);
    void createReport(String entryId,String header,String message,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void getMe(ServiceCallback<User> userServiceCallback);
    void getEntries(ServiceCallback<List<Entry>> listServiceCallback);
    void getLotteries(ServiceCallback<List<Lottery>> listServiceCallback);
    void getLottery(String lotteryId,ServiceCallback<Lottery> lotteryServiceCallback);
    void partipicateLottery(String lotteryId,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void deleteEntry(String entryId,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void getEntry(String entryId,ServiceCallback<Entry> entryServiceCallback);
    void updateEntryHeader(String header,String entryId,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void updateEntryMessage(String message,String entryId,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void updateEntryPrice(int price,String entryId,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void updateEntryServer(String serverId,String entryId,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void getNotifications(ServiceCallback<List<Notifications>> listServiceCallback);
    void forgetPasswordStepOne(String phoneNumber,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void forgetPasswordStepTwo(String phoneNumber,String smsCode,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void forgetPasswordStepThree(String phoneNumber,String password,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void updateProfile(String nickname,String name,String surname, String registerServerId, boolean isShowPassword,ServiceCallback<CommonResponse> commonResponseServiceCallback);

}
