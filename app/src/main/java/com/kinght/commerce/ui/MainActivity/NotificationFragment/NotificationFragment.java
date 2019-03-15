package com.kinght.commerce.ui.MainActivity.NotificationFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kinght.commerce.R;
import com.kinght.commerce.ui.base.BaseFragment;

import javax.inject.Inject;


public class NotificationFragment extends BaseFragment implements NotificationFragmentMvpView {


    @Inject
    NotificationFragmentMvpPresenter<NotificationFragmentMvpView> presenter;

    public NotificationFragment() {
        // Required empty public constructor
    }

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root= inflater.inflate(R.layout.fragment_notification, container, false);



        return root;
    }


}
