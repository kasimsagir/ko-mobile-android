package com.kinght.commerce.data.network.entities.Report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class ReportRequest {


    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("header")
    private String header;

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
