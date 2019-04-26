package com.kinght.commerce.data.network.entities.Event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventHours {
    @Expose
    @SerializedName("__v")
    private int __v;
    @Expose
    @SerializedName("eventHour")
    private String eventHour;
    @Expose
    @SerializedName("_id")
    private String _id;
    @Expose
    @SerializedName("isSelected")
    private boolean isSelected;

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getEventHour() {
        return eventHour;
    }

    public void setEventHour(String eventHour) {
        this.eventHour = eventHour;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
