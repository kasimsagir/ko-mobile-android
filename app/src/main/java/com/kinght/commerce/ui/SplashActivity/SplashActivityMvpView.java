package com.kinght.commerce.ui.SplashActivity;
import com.kinght.commerce.ui.base.MvpView;
public interface SplashActivityMvpView extends MvpView {
    void openMainActivity();

    void openSmsVerificationActivity();

    void openEntryDetail(String entryId);
}