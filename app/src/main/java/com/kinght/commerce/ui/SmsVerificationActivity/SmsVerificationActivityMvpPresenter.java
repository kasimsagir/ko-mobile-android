package com.kinght.commerce.ui.SmsVerificationActivity;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface SmsVerificationActivityMvpPresenter<V extends SmsVerificationActivityMvpView> extends MvpPresenter<V> {
    void verificateAccount(String regularText);
}