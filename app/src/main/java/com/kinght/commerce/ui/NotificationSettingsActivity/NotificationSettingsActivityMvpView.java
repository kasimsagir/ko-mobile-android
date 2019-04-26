package com.kinght.commerce.ui.NotificationSettingsActivity;
import com.kinght.commerce.data.network.entities.Settings.Settings;
import com.kinght.commerce.ui.base.MvpView;

import java.util.List;

public interface NotificationSettingsActivityMvpView extends MvpView {
    void loadDataToList(List<Settings> response);
}