package com.example.cryptocurrencytrackingsystem.Database;

import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.User;

import java.util.List;

public interface DataServiceInterface {
    //user
    User getUser(String login);
    void saveUser(User user);

    //admin
    User getAdminAccount(User admin);
    List<User> getUsers(int theSortField);
    void deleteAnAccount(Integer userId);
    void updateUser(User updatedUser);
    User getUser(Integer userId);

    //common
    List<Currency> getSortedCurrencies(int theSortField);

}
