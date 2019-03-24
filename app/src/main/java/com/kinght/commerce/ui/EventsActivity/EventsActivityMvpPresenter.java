package com.kinght.commerce.ui.EventsActivity;
import com.kinght.commerce.ui.base.MvpPresenter;
public interface EventsActivityMvpPresenter<V extends EventsActivityMvpView> extends MvpPresenter<V> {
    void getEvents();
}