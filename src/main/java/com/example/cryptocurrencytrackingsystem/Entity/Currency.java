package com.example.cryptocurrencytrackingsystem.Entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currency")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {

    @Id
    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "symbol")
    @JsonProperty("symbol")
    private String symbol;

    @Column(name = "current_price")
    @JsonProperty("current_price")
    private float current_price;

    @Column(name = "market_cap")
    @JsonProperty("market_cap")
    private float market_cap;

    @Column(name = "market_cap_rank")
    @JsonProperty("market_cap_rank")
    private int market_cap_rank;

    @Column(name = "ath")
    @JsonProperty("ath")
    private float ath;

    @Column(name = "atl")
    @JsonProperty("atl")
    private float atl;

    @Column(name = "high_24h")
    @JsonProperty("high_24h")
    private float high_24h;

    @Column(name = "low_24h")
    @JsonProperty("low_24h")
    private float low_24h;


    public Currency() {
    }

    public Currency(String name, String symbol, float current_price, float market_cap, int market_cap_rank, float ath, float atl, float high_24h, float low_24h) {
        this.name = name;
        this.symbol = symbol;
        this.current_price = current_price;
        this.market_cap = market_cap;
        this.market_cap_rank = market_cap_rank;
        this.ath = ath;
        this.atl = atl;
        this.high_24h = high_24h;
        this.low_24h = low_24h;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public float getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(float current_price) {
        this.current_price = current_price;
    }

    public float getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(float market_cap) {
        this.market_cap = market_cap;
    }

    public int getMarket_cap_rank() {
        return market_cap_rank;
    }

    public void setMarket_cap_rank(int market_cap_rank) {
        this.market_cap_rank = market_cap_rank;
    }

    public float getAth() {
        return ath;
    }

    public void setAth(float ath) {
        this.ath = ath;
    }

    public float getAtl() {
        return atl;
    }

    public void setAtl(float atl) {
        this.atl = atl;
    }

    public float getHigh_24h() {
        return high_24h;
    }

    public void setHigh_24h(float high_24h) {
        this.high_24h = high_24h;
    }

    public float getLow_24h() {
        return low_24h;
    }

    public void setLow_24h(float low_24h) {
        this.low_24h = low_24h;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", current_price=" + current_price +
                ", market_cap=" + market_cap +
                ", market_cap_rank=" + market_cap_rank +
                ", ath=" + ath +
                ", atl=" + atl +
                ", high_24h=" + high_24h +
                ", low_24h=" + low_24h +
                '}';
    }
}
