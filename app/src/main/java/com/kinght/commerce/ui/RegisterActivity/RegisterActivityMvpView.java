package com.kinght.commerce.ui.RegisterActivity;
import com.kinght.commerce.ui.base.MvpView;
public interface RegisterActivityMvpView extends MvpView {
    void showServerNameToUser(String name);

    void openSmsVerificationActivity();
}