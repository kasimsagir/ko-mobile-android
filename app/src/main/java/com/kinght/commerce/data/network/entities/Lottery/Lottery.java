package com.kinght.commerce.data.network.entities.Lottery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kinght.commerce.data.network.entities.Entries.User;

import java.util.List;

public class Lottery {
    @SerializedName("createdDate")
    @Expose
    private Long createdDate;
    @SerializedName("endDate")
    @Expose
    private Long endDate;
    @SerializedName("winner")
    @Expose
    private String winner;
    @SerializedName("participants")
    @Expose
    private List<User> participants = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
