package com.kinght.commerce.ui.ReportActivity;

import android.text.Editable;

import com.kinght.commerce.ui.base.MvpPresenter;
public interface ReportActivityMvpPresenter<V extends ReportActivityMvpView> extends MvpPresenter<V> {
    void reportEntry(String entryId, String text, String text1);
}