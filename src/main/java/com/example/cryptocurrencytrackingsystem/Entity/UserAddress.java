package com.example.cryptocurrencytrackingsystem.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.LinkedHashMap;

@Entity
@Table(name = "user_address", uniqueConstraints = {
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
    @Pattern(regexp = "^[0x]+[a-zA-Z0-9]*", message = "Invalid address pattern!")
    private String address;

    @Column(name = "chainName")
    private String chain;

    @Transient
    private final LinkedHashMap<String, String> chains;

    public UserAddress() {
        chains = new LinkedHashMap<>();
        chains.put("BNB Chain", "BNB Chain");
    }

    public LinkedHashMap<String, String> getChains() {
        return chains;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }
}