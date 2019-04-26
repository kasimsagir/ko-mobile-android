package com.kinght.commerce.ui.EventsActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Event.EventHours;
import com.kinght.commerce.data.network.entities.Event.Events;
import com.kinght.commerce.firebase.NotificationReceiver;
import com.kinght.commerce.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import static android.content.Context.ALARM_SERVICE;

public class EventsActivityPresenter<V extends EventsActivityMvpView> extends BasePresenter<V> implements EventsActivityMvpPresenter<V> {
    List<Events> eventList;
    List<EventHours> eventHoursList=new ArrayList<>();

    @Inject
    public EventsActivityPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void getEvents() {
        getMvpView().showLoading();
        getDataManager().getEvents(new ServiceCallback<List<Events>>() {
            @Override
            public void onSuccess(List<Events> response) {
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
    public void selectItem(EventHours item, int position) {
        if(item.getIsSelected()){
            eventHoursList.add(item);
        }else {
            eventHoursList.remove(item);
        }


        /*getDataManager().updateEventListCache(eventList);


        for (String day : item.getEventDays()) {
            for (Hour hour : item.getEventHours()) {
                if (hour.isSelected()) {
                    AlarmManager alarmManager;
                    Intent intent = new Intent(getMvpView().getActivity(), NotificationReceiver.class);
                    intent.putExtra("key", item.getEventName());
                    intent.putExtra("id",hour.getId());
                    int ticks = new Random().nextInt(30000000);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getMvpView().getActivity(), ticks, intent, 0);
                    alarmManager = (AlarmManager) getMvpView().getActivity().getSystemService(ALARM_SERVICE);


                    alarmManager.set(AlarmManager.RTC_WAKEUP, hour.getCurrentMilisTime(day), pendingIntent);
                }

            }

        }*/


    }

    @Override
    public void save() {
        getMvpView().showLoading();
        getDataManager().updateEventList(eventHoursList, new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {

                getMvpView().hideLoading();
                getMvpView().getActivity().onBackPressed();
            }

            @Override
            public void onSuccess() {
                getMvpView().hideLoading();
            }

            @Override
            public void onError(int code, String errorResponse) {
                getMvpView().showError(errorResponse);
                getMvpView().hideLoading();
            }
        });
    }


}