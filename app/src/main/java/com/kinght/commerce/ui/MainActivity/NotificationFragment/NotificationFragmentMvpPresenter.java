package com.kinght.commerce.ui.MainActivity.NotificationFragment;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface NotificationFragmentMvpPresenter<V extends NotificationFragmentMvpView> extends MvpPresenter<V> {
    void getNotifications();
}