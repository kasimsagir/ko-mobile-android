package com.kinght.commerce.ui.MainActivity.NotificationFragment;
import com.kinght.commerce.data.network.entities.Notification.Notifications;
import com.kinght.commerce.ui.base.MvpView;

import java.util.List;

public interface NotificationFragmentMvpView extends MvpView {
    void loadDataToList(List<Notifications> response);
}