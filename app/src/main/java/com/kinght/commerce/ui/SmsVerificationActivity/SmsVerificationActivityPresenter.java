package com.kinght.commerce.ui.SmsVerificationActivity;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.ui.base.BasePresenter;

import javax.inject.Inject;

public class SmsVerificationActivityPresenter<V extends SmsVerificationActivityMvpView> extends BasePresenter<V> implements SmsVerificationActivityMvpPresenter<V> {
    @Inject
    public SmsVerificationActivityPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void verificateAccount(String regularText) {
        getMvpView().showLoading();

        getDataManager().registerStepTwo(regularText, new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {
                getMvpView().openMainActivity();
                getMvpView().hideLoading();
            }

            @Override
            public void onSuccess() {
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