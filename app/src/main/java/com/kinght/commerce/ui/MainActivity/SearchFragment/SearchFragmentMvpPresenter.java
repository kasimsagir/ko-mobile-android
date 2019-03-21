package com.kinght.commerce.ui.MainActivity.SearchFragment;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface SearchFragmentMvpPresenter<V extends SearchFragmentMvpView> extends MvpPresenter<V> {
    void getServers();

    void getFilterEntries(String input);

    void getAllEntries();

    void getMe();
}