package com.kinght.commerce.data.network.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class RegisterObject {


    @Expose
    @SerializedName("pnsToken")
    private String pnsToken;
    @Expose
    @SerializedName("isShowPhoneNumber")
    private boolean isShowPhoneNumber;
    @Expose
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @Expose
    @SerializedName("nickname")
    private String nickname;
    @Expose
    @SerializedName("serverId")
    private String serverId;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("surname")
    private String surname;
    @Expose
    @SerializedName("name")
    private String name;

    public String getPnsToken() {
        return pnsToken;
    }

    public void setPnsToken(String pnsToken) {
        this.pnsToken = pnsToken;
    }

    public boolean getIsShowPhoneNumber() {
        return isShowPhoneNumber;
    }

    public void setIsShowPhoneNumber(boolean isShowPhoneNumber) {
        this.isShowPhoneNumber = isShowPhoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
