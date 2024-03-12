package com.utopy.cryptobot.model;


//https://criptoya.com/api
public class USDTtoARS {

    private Float ask;

    private Float totalAsk;

    private Float bid;

    private Float totalBid;

    private Integer time;

    public USDTtoARS(){
        super();
    }

    public USDTtoARS(Float ask, Float totalAsk, Float bid, Float totalBid, Integer time) {
        this.ask = ask;
        this.totalAsk = totalAsk;
        this.bid = bid;
        this.totalBid = totalBid;
        this.time = time;
    }

    public Float getAsk() {
        return ask;
    }

    public void setAsk(Float ask) {
        this.ask = ask;
    }

    public Float getTotalAsk() {
        return totalAsk;
    }

    public void setTotalAsk(Float totalAsk) {
        this.totalAsk = totalAsk;
    }

    public Float getBid() {
        return bid;
    }

    public void setBid(Float bid) {
        this.bid = bid;
    }

    public Float getTotalBid() {
        return totalBid;
    }

    public void setTotalBid(Float totalBid) {
        this.totalBid = totalBid;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    



    
}