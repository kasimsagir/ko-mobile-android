package com.kinght.commerce.ui.EventsActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.Event.Event;
import com.kinght.commerce.data.network.entities.Event.Events;
import com.kinght.commerce.data.network.entities.Event.Hour;
import com.kinght.commerce.firebase.NotificationReceiver;
import com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.utility.Constant;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import static android.content.Context.ALARM_SERVICE;

public class EventsActivityPresenter<V extends EventsActivityMvpView> extends BasePresenter<V> implements EventsActivityMvpPresenter<V> {
    List<Event> eventList;

    @Inject
    public EventsActivityPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void getEvents() {
        getMvpView().showLoading();
        getDataManager().getEvents(new ServiceCallback<List<Event>>() {
            @Override
            public void onSuccess(List<Event> response) {
                eventList = response;
                getMvpView().loadDataToList(response);
                getMvpView().hideLoading();
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {

                getMvpView().showError(errorResponse);
                getMvpView().hideLoading();
            }
        });
    }

    @Override
    public void selectItem(Event item, int position) {
        eventList.set(position, item);
        getDataManager().updateEventListCache(eventList);


        for (String day : item.getEventDays()) {
            for (Hour hour : item.getEventHours()) {
                if (hour.isSelected()) {
                    AlarmManager alarmManager;
                    Intent intent = new Intent(getMvpView().getActivity(), NotificationReceiver.class);
                    intent.putExtra("key", item.getEventName());
                    intent.putExtra("id",hour.getId());
                    int ticks = (int) System.currentTimeMillis();

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getMvpView().getActivity(), ticks, intent, 0);
                    alarmManager = (AlarmManager) getMvpView().getActivity().getSystemService(ALARM_SERVICE);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_WEEK, returnDayFromString(day));
                    calendar.set(Calendar.HOUR_OF_DAY, returnHourOfString(hour.getHour()));
                    calendar.set(Calendar.MINUTE, returnMinuteOfString(hour.getHour())- Constant.ALARM_INCREASE);

                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                }

            }

        }


    }


    public Integer returnDayFromString(String day) {
        switch (day) {
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

    public Integer returnHourOfString(String hour) {
        return Integer.parseInt(hour.split(":")[0]);
    }

    public Integer returnMinuteOfString(String hour) {
        return Integer.parseInt(hour.split(":")[1]);
    }
}