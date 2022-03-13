package com.example.cryptocurrencytrackingsystem.entity;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Size(min = 5, message = "Your login should be longer!")
    @NotBlank(message = "Fill the empty fields!")
    @Column(name = "login")
    private String login;


    @Size(min = 5, message = "Your password should be longer!")
    @NotBlank(message = "Fill the empty fields!")
    @Column(name = "password")
    private String password;


    @NotBlank(message = "Fill the empty fields!")
    @Email(message = "Wrong email format!")
    @Column(name = "email")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String firstName) {
        this.login = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String lastName) {
        this.password = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
