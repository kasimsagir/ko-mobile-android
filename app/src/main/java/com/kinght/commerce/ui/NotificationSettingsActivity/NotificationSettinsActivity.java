package com.kinght.commerce.ui.NotificationSettingsActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Event.Events;
import com.kinght.commerce.firebase.NotificationReceiver;
import com.kinght.commerce.ui.base.BaseActivity;

import java.util.Calendar;
import java.util.List;

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

    @Override
    public void loadDataToList(List<Events> response) {
        Log.d("veri","veri");



    }


    public Integer returnDayFromString(String day){
        switch (day){
            case "Monday":
                return Calendar.MONDAY;
            case "Sunday":
                return Calendar.SUNDAY;
            case "Tuesday":
                return Calendar.TUESDAY;
            case "Wednesday":
                return Calendar.WEDNESDAY;
            case "Thursday":
                return Calendar.THURSDAY;
            case "Friday":
                return Calendar.FRIDAY;
            case "Saturday":
                return Calendar.SATURDAY;
            default:
                return null;
        }
    }
}
