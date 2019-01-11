package com.kinght.commerce.ui.base;

public interface MvpPresenter<V extends MvpView>{
    void onAttach(V mvpView);
}
