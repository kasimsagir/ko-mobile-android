package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.Notification.Notifications;

import java.util.List;

public interface NotificationServices {

    void getNotifications(ServiceCallback<List<Notifications>> notificationCallBack);

}
