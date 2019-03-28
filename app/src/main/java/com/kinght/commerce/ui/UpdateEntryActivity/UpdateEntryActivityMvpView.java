package com.kinght.commerce.ui.UpdateEntryActivity;
import com.kinght.commerce.ui.base.MvpView;
public interface UpdateEntryActivityMvpView extends MvpView {
    void entryDetail(String header, String message, Integer price, String name);

    void getSelectedServerName(String name);
}