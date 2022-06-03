package com.example.cryptocurrencytrackingsystem.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.LinkedHashMap;

@Entity
@Getter
@Setter
@Table(name = "user_address", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "user_adres_id_uindex", columnNames = {"id"})
})
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "address")
    @NotBlank(message = "Fill empty field!")
    @Pattern(regexp = "^[0x]+[a-zA-Z\\d]*", message = "Invalid address pattern!")
    private String address;

    @Column(name = "chain_name")
    private String chain;

    @Transient
    private final LinkedHashMap<String, String> chains;

    public UserAddress() {
        chains = new LinkedHashMap<>();
        chains.put("BNB Chain", "BNB Chain");
    }
}