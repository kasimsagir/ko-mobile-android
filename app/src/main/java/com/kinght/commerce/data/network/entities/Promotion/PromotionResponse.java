package com.kinght.commerce.data.network.entities.Promotion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromotionResponse {


    @Expose
    @SerializedName("promotions")
    private List<Promotions> promotions;
    @Expose
    @SerializedName("statusCode")
    private int statusCode;
    @Expose
    @SerializedName("isSuccess")
    private boolean isSuccess;

    public List<Promotions> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotions> promotions) {
        this.promotions = promotions;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
