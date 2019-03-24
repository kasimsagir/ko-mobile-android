package com.kinght.commerce.ui.EventsActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Event.Events;
import com.kinght.commerce.firebase.NotificationReceiver;
import com.kinght.commerce.ui.adapters.EventsRecylerViewAdapter;
import com.kinght.commerce.ui.base.BaseActivity;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsActivity extends BaseActivity implements EventsActivityMvpView {

    @Inject
    EventsActivityMvpPresenter<EventsActivityMvpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_events_recyler_view)
    RecyclerView activityEventsRecylerView;

    EventsRecylerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);
        ((MvpApp) getApplication()).getActivityComponent().injectEventsActivity(this);

        presenter.onAttach(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenter.getEvents();
        adapter=new EventsRecylerViewAdapter(new EventsRecylerViewAdapter.ItemListener() {
            @Override
            public void onItemClick(Events item) {

                for(String day:item.getEventDays()){
                    for(String hour:item.getEventHours()){
                        AlarmManager alarmManager;
                        Intent intent = new Intent(EventsActivity.this, NotificationReceiver.class);
                        intent.putExtra("key", item.getEventName());
                        int ticks = (int) System.currentTimeMillis();

                        PendingIntent pendingIntent = PendingIntent.getBroadcast(EventsActivity.this, ticks, intent, 0);
                        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                        Calendar calendar=Calendar.getInstance();
                        calendar.set(Calendar.DAY_OF_WEEK,returnDayFromString(day));
                        calendar.set(Calendar.HOUR_OF_DAY,returnHourOfString(hour));
                        calendar.set(Calendar.MINUTE,returnMinuteOfString(hour));

                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),  pendingIntent);
                    }

                }

            }
        });




    }


    @Override
    public void loadDataToList(List<Events> response) {
        adapter.setData(response);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        activityEventsRecylerView.setLayoutManager(manager);
        activityEventsRecylerView.setAdapter(adapter);

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

    public Integer returnHourOfString(String hour){
        return Integer.parseInt(hour.split(":")[0]);
    }

    public Integer returnMinuteOfString(String hour){
        return Integer.parseInt(hour.split(":")[1]);
    }
}
