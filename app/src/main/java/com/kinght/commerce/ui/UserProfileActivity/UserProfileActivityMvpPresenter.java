package com.kinght.commerce.ui.UserProfileActivity;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface UserProfileActivityMvpPresenter<V extends UserProfileActivityMvpView> extends MvpPresenter<V> {
    void getUser(String userId);
}