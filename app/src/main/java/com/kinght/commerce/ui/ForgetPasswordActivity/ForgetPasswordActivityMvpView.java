package com.kinght.commerce.ui.ForgetPasswordActivity;
import com.kinght.commerce.ui.base.MvpView;
public interface ForgetPasswordActivityMvpView extends MvpView {
    void openLoginActivity();

    void showStepThree();

    void showStepTwo();

    void showOnlyStepThree();
}