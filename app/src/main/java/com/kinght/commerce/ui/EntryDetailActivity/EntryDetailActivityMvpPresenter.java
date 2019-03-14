package com.kinght.commerce.ui.EntryDetailActivity;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface EntryDetailActivityMvpPresenter<V extends EntryDetailActivityMvpView> extends MvpPresenter<V> {
    void getEntryDetail(String entryId);

    void showContactList();
}