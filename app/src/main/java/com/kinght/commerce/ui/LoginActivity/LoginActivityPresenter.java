package com.kinght.commerce.ui.LoginActivity;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.ui.base.BasePresenter;

import javax.inject.Inject;

public class LoginActivityPresenter<V extends LoginActivityMvpView> extends BasePresenter<V> implements LoginActivityMvpPresenter<V> {
    @Inject
    public LoginActivityPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void login(String phoneNumber, String password) {
        getMvpView().showLoading();
        getDataManager().login(phoneNumber, password, new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {
                getMvpView().openMainActivity();
                getMvpView().hideLoading();
            }

            @Override
            public void onSuccess() {
                getMvpView().openMainActivity();
                getMvpView().hideLoading();
            }

            @Override
            public void onError(int code, String errorResponse) {
                getMvpView().showError(errorResponse);
                getMvpView().hideLoading();
            }
        });
    }
}