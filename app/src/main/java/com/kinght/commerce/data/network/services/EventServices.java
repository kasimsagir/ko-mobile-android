package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Event.EventHours;
import com.kinght.commerce.data.network.entities.Event.Events;

import java.util.List;

public interface EventServices {

    void getEvents(String userId,ServiceCallback<List<Events>> eventsServiceCallback);
    void updateEventList(String userId, List<EventHours> hoursList,ServiceCallback<CommonResponse> commonResponseServiceCallback);
}
