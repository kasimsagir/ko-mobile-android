package com.kinght.commerce.data.network.entities.Entries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notifications {
    @Expose
    @SerializedName("__v")
    private int __v;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("_id")
    private String _id;

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
