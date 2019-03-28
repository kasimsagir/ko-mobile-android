package com.kinght.commerce.ui.UpdateEntryActivity;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface UpdateEntryActivityMvpPresenter<V extends UpdateEntryActivityMvpView> extends MvpPresenter<V> {
    void getEntryDetail(String entryId);

    void updateEntry(String regularText, String regularText1, Integer tryParse);

    void getServers();
}