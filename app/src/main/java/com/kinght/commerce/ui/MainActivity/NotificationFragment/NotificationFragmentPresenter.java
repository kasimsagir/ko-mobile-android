package com.kinght.commerce.ui.MainActivity.NotificationFragment;
import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.ui.base.BasePresenter;
import javax.inject.Inject;
public class NotificationFragmentPresenter<V extends NotificationFragmentMvpView> extends BasePresenter<V> implements NotificationFragmentMvpPresenter<V> {@Inject public NotificationFragmentPresenter(DataManager dataManager) {super(dataManager);}}