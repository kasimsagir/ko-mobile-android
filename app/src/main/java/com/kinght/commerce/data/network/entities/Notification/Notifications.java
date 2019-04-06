package com.kinght.commerce.data.network.entities.Notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notifications {
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("_id")
    private String _id;
    @Expose
    @SerializedName("relatedObjectId")
    private int relatedObjectId;


    public int getRelatedObjectId() {
        return relatedObjectId;
    }

    public void setRelatedObjectId(int relatedObjectId) {
        this.relatedObjectId = relatedObjectId;
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
