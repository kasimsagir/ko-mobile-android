package com.kinght.commerce.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;


import com.kinght.commerce.utility.CommonUtils;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity baseActivity;
    private ProgressDialog progressDialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.baseActivity = activity;
            activity.onFragmentAttached();
        }
    }


    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = CommonUtils.showLoadingDialog(this.getContext());
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    public void showDialogWithChoose(String title, String description, String possitiveButtonText, String negativeButtonText, DialogCallback dialogCallback) {
        if (baseActivity != null) {
            baseActivity.showDialogWithChoose(title,description,possitiveButtonText,negativeButtonText,dialogCallback);
        }
    }

    @Override
    public void showDialogWithOutChoose(String title, String description, String buttonText,DialogCallback dialogCallback) {
        if (baseActivity != null) {
            baseActivity.showDialogWithOutChoose(title,description,buttonText,dialogCallback);
        }

    }

    @Override
    public void showInformation(String text) {
        if (baseActivity != null) {
            baseActivity.showError(text);
        }

    }



    @Override
    public void showError(String message) {
        if (baseActivity != null) {
            baseActivity.showError(message);
        }
    }

    @Override
    public void hideSystemUI() {
        View decorView = getActivity().getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE

                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public String getStringFromResourceId(int resourceId) {
        return baseActivity.getStringFromResourceId(resourceId);
    }

    @Override
    public Typeface getBoldMyriadFont() {
        return baseActivity.getBoldMyriadFont();
    }

    @Override
    public Typeface getRegularMyriadFont() {
        return baseActivity.getRegularMyriadFont();
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    @Override
    public void showInputDialog(String title, String hint, DialogStringCallback dialogStringCallback) {
        baseActivity.showInputDialog(title,hint,dialogStringCallback);
    }

    @Override
    public void showListDialog(List<String> itemList, String title, ListSelectItem<Integer> listSelectItem) {
        baseActivity.showListDialog(itemList,title,listSelectItem);
    }
}