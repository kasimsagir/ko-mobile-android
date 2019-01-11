package com.kinght.commerce.ui.base;


import android.graphics.Typeface;

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

}
