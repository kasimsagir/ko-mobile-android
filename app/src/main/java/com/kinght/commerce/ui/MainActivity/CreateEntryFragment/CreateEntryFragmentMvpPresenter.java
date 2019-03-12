package com.kinght.commerce.ui.MainActivity.CreateEntryFragment;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface CreateEntryFragmentMvpPresenter<V extends CreateEntryFragmentMvpView> extends MvpPresenter<V> {
    void selectImageFrom();
}