package com.kinght.commerce.ui.RegisterActivity;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface RegisterActivityMvpPresenter<V extends RegisterActivityMvpView> extends MvpPresenter<V> {
    void showServerList();

    void register(String regularText, String regularText1, String regularText2, String regularText3, String regularText4, boolean isCheckPhone);
}