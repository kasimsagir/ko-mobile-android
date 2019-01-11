package com.kinght.commerce.ui.LoginActivity;
import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.ui.base.BasePresenter;
import javax.inject.Inject;
public class LoginActivityPresenter<V extends LoginActivityMvpView> extends BasePresenter<V> implements LoginActivityMvpPresenter<V> {@Inject public LoginActivityPresenter(DataManager dataManager) {super(dataManager);}}