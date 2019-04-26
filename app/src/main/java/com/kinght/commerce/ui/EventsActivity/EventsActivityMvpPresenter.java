package com.kinght.commerce.ui.EventsActivity;
import com.kinght.commerce.data.network.entities.Event.EventHours;
import com.kinght.commerce.data.network.entities.Event.Events;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface EventsActivityMvpPresenter<V extends EventsActivityMvpView> extends MvpPresenter<V> {
    void getEvents();

    void selectItem(EventHours item, int position);

    void save();
}