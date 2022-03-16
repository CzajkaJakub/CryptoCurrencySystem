package com.example.cryptocurrencytrackingsystem.Database;

import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.User;

import java.util.List;

public interface UserServiceInterface {
    //user
    User getUser(String login);
    void saveUser(User user);

    //admin
    User getAdminAccount();
    List<User> getUsers(int theSortField);
    void deleteAnAccount(Integer userId);
    void updateUser(User updatedUser);
    User getUser(Integer userId);

    //common
    List<Currency> getCurrencies();
    void updateCurrencyInDatabase(List<Currency> currency);

}
