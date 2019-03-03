package com.kinght.commerce.data.network.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class LoginRequest {


    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("phoneNumber")
    private String phoneNumber;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
