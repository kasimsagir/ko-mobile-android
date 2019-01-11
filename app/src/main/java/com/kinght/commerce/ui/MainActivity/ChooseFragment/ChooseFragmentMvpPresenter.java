package com.kinght.commerce.ui.MainActivity.ChooseFragment;

import com.kinght.commerce.ui.base.MvpPresenter;

public interface ChooseFragmentMvpPresenter<V extends ChooseFragmentMvpView> extends MvpPresenter<V> {
    void openLogin();
    void openRegister();
}