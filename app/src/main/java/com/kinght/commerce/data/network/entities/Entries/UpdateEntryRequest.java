package com.kinght.commerce.data.network.entities.Entries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class UpdateEntryRequest {


    @Expose
    @SerializedName("server")
    private String server;
    @Expose
    @SerializedName("price")
    private int price;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("header")
    private String header;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
