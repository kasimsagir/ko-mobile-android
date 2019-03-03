package com.kinght.commerce.data.network.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class ForgetPasswordRequest {


    @Expose
    @SerializedName("password")
    private int password;

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
