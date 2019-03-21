package com.kinght.commerce.data.network.entities.Event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class EventResponse {


    @Expose
    @SerializedName("events")
    private List<Events> events;

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }
}
