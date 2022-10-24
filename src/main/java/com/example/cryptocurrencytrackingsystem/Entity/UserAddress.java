package com.example.cryptocurrencytrackingsystem.Entity;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table( name = "user_address", schema = "public", uniqueConstraints =
{ @UniqueConstraint( name = "user_adres_id_uindex", columnNames =
{ "id" } ) } )
public class UserAddress
{

    @Transient
    private final LinkedHashMap< String, String > chains;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", nullable = false )
    private Integer id;
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    private User user;
    @Column( name = "address" )
    @NotBlank( message = "Fill empty field!" )
    @Pattern( regexp = "^[0x]+[a-zA-Z\\d]*", message = "Invalid address pattern!" )
    private String address;
    @Column( name = "chain_name" )
    private String chain;

    public UserAddress()
    {
        chains = new LinkedHashMap<>();
        chains.put( "BNB Chain", "BNB Chain" );
    }
}