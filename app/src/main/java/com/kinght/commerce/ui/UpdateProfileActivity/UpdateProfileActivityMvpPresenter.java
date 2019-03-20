package com.kinght.commerce.ui.UpdateProfileActivity;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface UpdateProfileActivityMvpPresenter<V extends UpdateProfileActivityMvpView> extends MvpPresenter<V> {
    void getMe();

    void showServerList();

    void updateUser(String toString, String toString1, String toString2, boolean isSelectedShowNumber);
}