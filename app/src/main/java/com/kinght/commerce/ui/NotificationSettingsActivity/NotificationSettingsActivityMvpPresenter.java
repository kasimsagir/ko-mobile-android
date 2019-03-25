package com.kinght.commerce.ui.NotificationSettingsActivity;
import com.kinght.commerce.data.network.entities.Settings.Settings;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface NotificationSettingsActivityMvpPresenter<V extends NotificationSettingsActivityMvpView> extends MvpPresenter<V> {
    void getSettings();

    void setPositionData(Settings item, int position);

    void updateSettings();
}