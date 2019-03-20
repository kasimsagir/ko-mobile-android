package com.kinght.commerce.ui.ForgetPasswordActivity;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface ForgetPasswordActivityMvpPresenter<V extends ForgetPasswordActivityMvpView> extends MvpPresenter<V> {
    void forgetPasswordStepOne(String toString);

    void forgetPasswordStepTwo(String toString);

    void forgetPasswordStepThree(String toString, String toString1);

    void configurationForChangePassword();

    void changePassword(String toString, String toString1);
}