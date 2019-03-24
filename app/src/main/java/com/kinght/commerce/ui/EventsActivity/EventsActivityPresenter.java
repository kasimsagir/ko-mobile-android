package com.kinght.commerce.ui.EventsActivity;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.Event.Events;
import com.kinght.commerce.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

public class EventsActivityPresenter<V extends EventsActivityMvpView> extends BasePresenter<V> implements EventsActivityMvpPresenter<V> {
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
}