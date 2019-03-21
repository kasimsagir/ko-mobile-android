package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.Event.Events;

import java.util.List;

public interface EventServices {

    void getEvents(ServiceCallback<List<Events>> eventsServiceCallback);
}
