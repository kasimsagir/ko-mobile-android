package com.kinght.commerce.ui.NotificationSettingsActivity;

import android.os.Bundle;

import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.base.BaseActivity;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationSettinsActivity extends BaseActivity implements NotificationSettingsActivityMvpView {

    @Inject
    NotificationSettingsActivityMvpPresenter<NotificationSettingsActivityMvpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settins);
        ButterKnife.bind(this);

        ((MvpApp) getApplication()).getActivityComponent().injectNotificationSettinsActivity(this);
        presenter.onAttach(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}
