package com.example.cryptocurrencytrackingsystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.NumberFormat;
import java.util.Locale;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "currency", schema = "public")
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

    @Column(name = "image")
    @JsonProperty("image")
    private String image;



    public String getCurrent_price() {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(current_price);
    }


    public String getMarket_cap() {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(market_cap);
    }

    public String getAth() {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(ath);
    }

    public String getAtl() {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(atl);
    }

    public String getHigh_24h() {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(high_24h);
    }

    public String getLow_24h() {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(low_24h);
    }
}
