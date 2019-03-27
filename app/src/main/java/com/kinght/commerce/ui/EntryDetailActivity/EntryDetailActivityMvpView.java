package com.kinght.commerce.ui.EntryDetailActivity;
import com.kinght.commerce.ui.base.MvpView;
public interface EntryDetailActivityMvpView extends MvpView {


    void loadEntryData(String name, String surname, long createdDate, String nickname, String phoneNumber, String name1, String entryImageUrl, String header, String message, Integer price);

    void showUserProfileActivity(String userId);

    void entryOwnerConfiguration();
}