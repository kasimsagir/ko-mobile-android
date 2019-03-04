package com.kinght.commerce.data.network.entities.Servers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class CreateEntryRequest {


    @Expose
    @SerializedName("imageBase64")
    private String imageBase64;
    @Expose
    @SerializedName("price")
    private int price;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("header")
    private String header;

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
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
