package com.kinght.commerce.ui.NotificationSettingsActivity;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface NotificationSettingsActivityMvpPresenter<V extends NotificationSettingsActivityMvpView> extends MvpPresenter<V> {
    void getEvents();
}