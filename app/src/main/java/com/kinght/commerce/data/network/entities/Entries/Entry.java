package com.kinght.commerce.data.network.entities.Entries;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kinght.commerce.data.network.entities.Servers.Servers;


public class Entry {
    @SerializedName("creator")
    @Expose
    private User creator;
    @SerializedName("viewed")
    @Expose
    private Integer viewed;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("createdDate")
    @Expose
    private long createdDate;
    @SerializedName("isDisable")
    @Expose
    private Boolean isDisable;
    @SerializedName("entryImageUrl")
    @Expose
    private String entryImageUrl;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("server")
    @Expose
    private Servers servers;

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getDisable() {
        return isDisable;
    }

    public void setDisable(Boolean disable) {
        isDisable = disable;
    }

    public Servers getServers() {
        return servers;
    }

    public void setServers(Servers servers) {
        this.servers = servers;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Integer createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Boolean isDisable) {
        this.isDisable = isDisable;
    }

    public String getEntryImageUrl() {
        return entryImageUrl;
    }

    public void setEntryImageUrl(String entryImageUrl) {
        this.entryImageUrl = entryImageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
