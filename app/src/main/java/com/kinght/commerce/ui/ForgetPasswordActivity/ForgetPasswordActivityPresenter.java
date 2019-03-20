package com.kinght.commerce.ui.ForgetPasswordActivity;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.ui.base.BasePresenter;

import javax.inject.Inject;

public class ForgetPasswordActivityPresenter<V extends ForgetPasswordActivityMvpView> extends BasePresenter<V> implements ForgetPasswordActivityMvpPresenter<V> {
    String phoneNumber;

    @Inject
    public ForgetPasswordActivityPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void forgetPasswordStepOne(String toString) {
        getMvpView().showLoading();
        phoneNumber = toString.replace("(", "").replace(")", "").replace("-", "");
        getDataManager().forgetPasswordStepOne(phoneNumber, new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {

                getMvpView().hideLoading();
                getMvpView().showStepTwo();
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {

                getMvpView().showError(errorResponse);
                getMvpView().hideLoading();
            }
        });
    }

    @Override
    public void forgetPasswordStepTwo(String toString) {
        getMvpView().showLoading();
        getDataManager().forgetPasswordStepTwo(phoneNumber, toString, new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {
                getMvpView().hideLoading();
                getMvpView().showStepThree();

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {
                getMvpView().showError(errorResponse);
                getMvpView().hideLoading();
            }
        });
    }

    @Override
    public void forgetPasswordStepThree(String passwordOne, String passwordTwo) {
        if(!passwordOne.equals(passwordTwo)){
            getMvpView().showError("Şifreleriniz uyuşmuyor lütfen şifrelerinizi kontrol ediniz");
        }else {
            getMvpView().showLoading();
            getDataManager().forgetPasswordStepThree(phoneNumber, passwordOne, new ServiceCallback<CommonResponse>() {
                @Override
                public void onSuccess(CommonResponse response) {
                    getMvpView().hideLoading();
                    getMvpView().openLoginActivity();

                }

                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(int code, String errorResponse) {
                    getMvpView().showError(errorResponse);
                    getMvpView().hideLoading();
                }
            });
        }
    }

    @Override
    public void configurationForChangePassword() {

        getMvpView().showOnlyStepThree();
    }

    @Override
    public void changePassword(String passwordOne, String passwordTwo) {
        if(!passwordOne.equals(passwordTwo)){
            getMvpView().showError("Şifreleriniz uyuşmuyor lütfen şifrelerinizi kontrol ediniz");
        }else {
            getMvpView().showLoading();

            getDataManager().getMe(new ServiceCallback<User>() {
                @Override
                public void onSuccess(User response) {
                    getDataManager().forgetPasswordStepThree(response.getPhoneNumber(), passwordOne, new ServiceCallback<CommonResponse>() {
                        @Override
                        public void onSuccess(CommonResponse response) {
                            getMvpView().hideLoading();
                            getMvpView().getActivity().onBackPressed();

                        }

                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(int code, String errorResponse) {
                            getMvpView().showError(errorResponse);
                            getMvpView().hideLoading();
                        }
                    });
                }

                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(int code, String errorResponse) {
                    getMvpView().showError(errorResponse);
                    getMvpView().hideLoading();
                }
            });
        }
    }
}