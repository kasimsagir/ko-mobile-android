package com.kinght.commerce.ui.EventsActivity;
import com.kinght.commerce.data.network.entities.Event.Event;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface EventsActivityMvpPresenter<V extends EventsActivityMvpView> extends MvpPresenter<V> {
    void getEvents();

    void selectItem(Event item, int position);
}