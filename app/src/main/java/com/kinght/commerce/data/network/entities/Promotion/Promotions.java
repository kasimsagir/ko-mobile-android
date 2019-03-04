package com.kinght.commerce.data.network.entities.Promotion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Promotions {
    @Expose
    @SerializedName("__v")
    private int __v;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("price")
    private String price;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("_id")
    private String _id;
    @Expose
    @SerializedName("coin")
    private Coin coin;

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }
}
