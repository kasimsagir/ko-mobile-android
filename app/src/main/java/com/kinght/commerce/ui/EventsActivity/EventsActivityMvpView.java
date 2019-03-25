package com.kinght.commerce.ui.EventsActivity;
import com.kinght.commerce.data.network.entities.Event.Event;
import com.kinght.commerce.data.network.entities.Event.Events;
import com.kinght.commerce.ui.base.MvpView;

import java.util.List;

public interface EventsActivityMvpView extends MvpView {
    void loadDataToList(List<Event> response);
}