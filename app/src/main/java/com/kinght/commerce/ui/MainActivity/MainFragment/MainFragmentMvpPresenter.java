package com.kinght.commerce.ui.MainActivity.MainFragment;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface MainFragmentMvpPresenter<V extends MainFragmentMvpView> extends MvpPresenter<V> {
    void getServerList();

    void getEntries();
    void getServerEntries(String serverId);

    void getCoinDetail();

    void refresh();
}