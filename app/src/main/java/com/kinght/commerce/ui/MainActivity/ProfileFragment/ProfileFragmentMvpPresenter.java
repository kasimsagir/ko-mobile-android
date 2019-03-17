package com.kinght.commerce.ui.MainActivity.ProfileFragment;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface ProfileFragmentMvpPresenter<V extends ProfileFragmentMvpView> extends MvpPresenter<V> {
    void getMe();
}