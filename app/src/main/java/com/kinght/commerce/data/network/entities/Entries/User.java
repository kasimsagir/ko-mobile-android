package com.kinght.commerce.data.network.entities.Entries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kinght.commerce.data.network.entities.Promotion.Coin;

import java.util.List;

import androidx.annotation.Nullable;

public class User {
    @Expose
    @SerializedName("__v")
    private int __v;
    @Expose
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @Expose
    @SerializedName("_id")
    private String _id;
    @Expose
    @SerializedName("notifications")
    private List<Notifications> notifications;
    @Expose
    @SerializedName("isShowPhoneNumber")
    private boolean isShowPhoneNumber;
    @Expose
    @SerializedName("nickname")
    private String nickname;
    @Expose
    @SerializedName("surname")
    private String surname;
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("entries")
    private List<Entry> entryList;

    @Expose(deserialize = false)
    @SerializedName("coin")

    private Coin coin;

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public boolean isShowPhoneNumber() {
        return isShowPhoneNumber;
    }

    public void setShowPhoneNumber(boolean showPhoneNumber) {
        isShowPhoneNumber = showPhoneNumber;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<Notifications> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notifications> notifications) {
        this.notifications = notifications;
    }

    public boolean getIsShowPhoneNumber() {
        return isShowPhoneNumber;
    }

    public void setIsShowPhoneNumber(boolean isShowPhoneNumber) {
        this.isShowPhoneNumber = isShowPhoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
