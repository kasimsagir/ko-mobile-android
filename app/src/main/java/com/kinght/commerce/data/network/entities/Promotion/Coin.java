package com.kinght.commerce.data.network.entities.Promotion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coin {
    @Expose
    @SerializedName("__v")
    private int __v;
    @Expose
    @SerializedName("_id")
    private String _id;
    @Expose
    @SerializedName("value")
    private int value;

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
