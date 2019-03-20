package com.kinght.commerce.ui.NotificationSettingsActivity;
import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.ui.base.BasePresenter;
import javax.inject.Inject;
public class NotificationSettingsActivityPresenter<V extends NotificationSettingsActivityMvpView> extends BasePresenter<V> implements NotificationSettingsActivityMvpPresenter<V> {@Inject public NotificationSettingsActivityPresenter(DataManager dataManager) {super(dataManager);}}