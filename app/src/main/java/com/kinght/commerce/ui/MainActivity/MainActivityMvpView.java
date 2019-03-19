package com.kinght.commerce.ui.MainActivity;

import com.kinght.commerce.ui.base.MvpView;

public interface MainActivityMvpView extends MvpView {
    void openMainFragment();
   // void openLoginActivity();
    void openChooseFragment();
    void openAccountFragment();

    void openSearchFragment();

    void openNotificationFragment();

    void createEntryFragment();

    void openLoginActivity();
}