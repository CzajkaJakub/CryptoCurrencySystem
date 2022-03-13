package com.example.cryptocurrencytrackingsystem.Entity;


import javax.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "currency_name")
    private String currency_name;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Amount")
    private Double amount;

    @Column(name = "Value")
    private Double value;


    public Currency(Integer id, String currency_name, Double price, Double amount, Double value) {
        this.id = id;
        this.currency_name = currency_name;
        this.price = price;
        this.amount = amount;
        this.value = value;
    }

    public Currency() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", currency_name='" + currency_name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", value=" + value +
                '}';
    }
}
