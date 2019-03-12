package com.kinght.commerce.ui.base;


import android.app.Activity;
import android.graphics.Typeface;

import java.util.List;

public interface MvpView {
    void showLoading();

    void hideLoading();

    void showDialogWithChoose(String title, String description, String possitiveButtonText, String negativeButtonText, DialogCallback dialogCallback);

    void showDialogWithOutChoose(String title, String description, String buttonText, DialogCallback dialogCallback);

    void showInformation(String text);

    void showError(String text);

    void hideSystemUI();

    Typeface getBoldMyriadFont();

    Typeface getRegularMyriadFont();

    String getStringFromResourceId(int resourceId);




    void showListDialog(List<String> itemList, String title, ListSelectItem<Integer> listSelectItem);

    void showInputDialog(String title, String hint, DialogStringCallback dialogStringCallback);

    Activity getActivity();

}
