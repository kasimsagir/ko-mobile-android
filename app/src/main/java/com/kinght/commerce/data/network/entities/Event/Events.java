package com.kinght.commerce.data.network.entities.Event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Events {
    @Expose
    @SerializedName("eventHours")
    private List<String> eventHours;
    @Expose
    @SerializedName("eventDays")
    private List<String> eventDays;
    @Expose
    @SerializedName("eventName")
    private String eventName;

    private boolean isSelected=false;


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public List<String> getEventHours() {
        return eventHours;
    }

    public void setEventHours(List<String> eventHours) {
        this.eventHours = eventHours;
    }

    public List<String> getEventDays() {
        return eventDays;
    }

    public void setEventDays(List<String> eventDays) {
        this.eventDays = eventDays;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
