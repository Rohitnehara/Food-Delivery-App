package com.example.project.Domain;

import java.io.Serializable;

public class orderInfo implements Serializable {

    private String title;
    private Double fee;

    public orderInfo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public orderInfo(String title, Double fee, int numberInCart) {
        this.title = title;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }

    private int numberInCart;
}
