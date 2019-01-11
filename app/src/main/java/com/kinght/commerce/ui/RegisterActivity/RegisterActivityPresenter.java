package com.kinght.commerce.ui.RegisterActivity;
import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.ui.base.BasePresenter;
import javax.inject.Inject;
public class RegisterActivityPresenter<V extends RegisterActivityMvpView> extends BasePresenter<V> implements RegisterActivityMvpPresenter<V> {@Inject public RegisterActivityPresenter(DataManager dataManager) {super(dataManager);}}