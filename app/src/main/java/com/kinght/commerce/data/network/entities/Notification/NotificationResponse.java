package com.kinght.commerce.data.network.entities.Notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class NotificationResponse {


    @Expose
    @SerializedName("notifications")
    private List<Notifications> notifications;
    @Expose
    @SerializedName("statusCode")
    private int statusCode;
    @Expose
    @SerializedName("isSuccess")
    private boolean isSuccess;

    public List<Notifications> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notifications> notifications) {
        this.notifications = notifications;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
