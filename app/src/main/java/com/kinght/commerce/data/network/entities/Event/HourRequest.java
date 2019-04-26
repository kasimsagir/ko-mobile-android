package com.kinght.commerce.data.network.entities.Event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class HourRequest {


    @Expose
    @SerializedName("hourList")
    private List<HourList> hourList;

    public List<HourList> getHourList() {
        return hourList;
    }

    public void setHourList(List<HourList> hourList) {
        this.hourList = hourList;
    }
}
