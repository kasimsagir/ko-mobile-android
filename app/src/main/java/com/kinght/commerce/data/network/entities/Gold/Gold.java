package com.kinght.commerce.data.network.entities.Gold;

public class Gold {

    private int gold;
    private String price;
    private String id;

    public Gold(int gold, String price, String id) {
        this.gold = gold;
        this.price = price;
        this.id = id;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
