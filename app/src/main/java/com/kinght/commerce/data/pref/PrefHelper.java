package com.kinght.commerce.data.pref;

public interface PrefHelper {

    void saveAuthorizationKey(String authKey);
    String getAuthorizationKey();

    void saveUdid(String udid);
    String getUdid();

    void saveEventListCache(String eventList);
    String getEventListCache();

    void saveUserId(String id);
    String getUserId();
}
