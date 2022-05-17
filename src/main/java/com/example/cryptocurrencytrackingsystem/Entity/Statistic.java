package com.example.cryptocurrencytrackingsystem.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "statistics")
public class Statistic {
    @Id
    @Column(name = "stat_name", nullable = false, length = 64)
    private String id;

    @Column(name = "value", nullable = false)
    private Integer value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}