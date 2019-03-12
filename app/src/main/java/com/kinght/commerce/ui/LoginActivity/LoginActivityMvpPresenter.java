package com.kinght.commerce.ui.LoginActivity;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface LoginActivityMvpPresenter<V extends LoginActivityMvpView> extends MvpPresenter<V> {
    void login(String regularText, String regularText1);
}