package com.example.cryptocurrencytrackingsystem.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table( name = "statistics" )
public class Statistic
{

    @Id
    @Column( name = "stat_name", nullable = false, length = 64 )
    private String id;

    @Column( name = "value", nullable = false )
    private Integer value;
}