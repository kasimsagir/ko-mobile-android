package com.kinght.commerce.ui.MainActivity;

import com.kinght.commerce.ui.base.MvpPresenter;

public interface MainActivityMvpPresenter<V extends MainActivityMvpView> extends MvpPresenter<V> {

void mainFragment();
void chooseFragment();
void addFragment();

}