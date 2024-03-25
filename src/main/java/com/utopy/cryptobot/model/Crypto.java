package com.utopy.cryptobot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Crypto {

    @Id
    private String symbol;

    private Double price;

    private Double arsPrice;


    public Crypto(){
        super();
    }

    public Crypto(String symbol, Double price){
        this.price = price;
        this.symbol = symbol;
        this.arsPrice = 0.0;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getArsPrice() {
        return arsPrice;
    }

    public void setArsPrice(Double arsPrice) {
        this.arsPrice = arsPrice;
    }







}
