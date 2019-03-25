package com.kinght.commerce.ui.NotificationSettingsActivity;

import android.os.Bundle;
import android.widget.Button;

import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Settings.Settings;
import com.kinght.commerce.ui.adapters.ServerNotificationRecylerViewAdapters;
import com.kinght.commerce.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationSettinsActivity extends BaseActivity implements NotificationSettingsActivityMvpView {

    @Inject
    NotificationSettingsActivityMvpPresenter<NotificationSettingsActivityMvpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_notification_recylerview)
    RecyclerView activityNotificationRecylerview;

    ServerNotificationRecylerViewAdapters adapter;
    @BindView(R.id.activity_notification_save_button)
    Button activityNotificationSaveButton;

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

        adapter = new ServerNotificationRecylerViewAdapters(new ServerNotificationRecylerViewAdapters.ItemListener() {
            @Override
            public void onItemClick(Settings item, int position) {
                presenter.setPositionData(item, position);
            }
        });
        presenter.getSettings();


    }


    @Override
    public void loadDataToList(List<Settings> response) {
        adapter.setData(response);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        activityNotificationRecylerview.setLayoutManager(manager);
        activityNotificationRecylerview.setAdapter(adapter);
    }

    @OnClick(R.id.activity_notification_save_button)
    public void onViewClicked() {
        presenter.updateSettings();
    }
}
