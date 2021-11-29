package com.example.project.module;

public class products {
    String picture,dishname,price,discriptions,offer;

    public products(String picture, String dishname, String price, String discriptions, String offer) {
        this.picture = picture;
        this.dishname = dishname;
        this.price = price;
        this.discriptions = discriptions;
        this.offer = offer;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscriptions() {
        return discriptions;
    }

    public void setDiscriptions(String discriptions) {
        this.discriptions = discriptions;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }
}
