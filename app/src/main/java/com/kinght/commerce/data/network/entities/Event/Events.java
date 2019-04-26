package com.kinght.commerce.data.network.entities.Event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Events {
    @Expose
    @SerializedName("__v")
    private int __v;
    @Expose
    @SerializedName("eventName")
    private String eventName;
    @Expose
    @SerializedName("_id")
    private String _id;
    @Expose
    @SerializedName("eventHours")
    private List<EventHours> eventHours;
    @Expose
    @SerializedName("eventDays")
    private List<String> eventDays;

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<EventHours> getEventHours() {
        return eventHours;
    }

    public void setEventHours(List<EventHours> eventHours) {
        this.eventHours = eventHours;
    }

    public List<String> getEventDays() {
        return eventDays;
    }

    public void setEventDays(List<String> eventDays) {
        this.eventDays = eventDays;
    }
}
