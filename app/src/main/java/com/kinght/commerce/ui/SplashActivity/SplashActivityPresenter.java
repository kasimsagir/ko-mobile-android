package com.kinght.commerce.ui.SplashActivity;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.utility.CommonUtils;

import javax.inject.Inject;

public class SplashActivityPresenter<V extends SplashActivityMvpView> extends BasePresenter<V> implements SplashActivityMvpPresenter<V> {
    @Inject
    public SplashActivityPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void splashActivity() {

        getMvpView().hideSystemUI();
        getDataManager().startApplication(CommonUtils.getPnsToken(), new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {
                getMvpView().openMainActivity();
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {
                if(code == 401){
                    getMvpView().openMainActivity();
                }else if(code == 452){
                    getMvpView().openSmsVerificationActivity();
                }
            }
        });
    }

    @Override
    public void openEntryDetail(String entryId) {
        getMvpView().hideSystemUI();
        getDataManager().startApplication(CommonUtils.getPnsToken(), new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {
                getMvpView().openEntryDetail(entryId);
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {
                if(code == 401){
                    getMvpView().openMainActivity();
                }else if(code == 452){
                    getMvpView().openSmsVerificationActivity();
                }
            }
        });
    }
}