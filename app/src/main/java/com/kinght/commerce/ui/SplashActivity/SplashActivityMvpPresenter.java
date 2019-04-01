package com.kinght.commerce.ui.SplashActivity;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface SplashActivityMvpPresenter<V extends SplashActivityMvpView> extends MvpPresenter<V> {
    void splashActivity();
}