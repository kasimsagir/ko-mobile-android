package com.kinght.commerce.data.pref;

public interface PrefHelper {

    void saveAuthorizationKey(String authKey);
    String getAuthorizationKey();

    void saveUdid(String udid);
    String getUdid();

}
