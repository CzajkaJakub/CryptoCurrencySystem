package com.example.cryptocurrencytrackingsystem.Database.DAOS;

import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.User;

import java.util.List;

public interface UserDAO {
    //user
    User getUser(String login);
    void saveUser(User user);


    //admin
    User getAdminAccount();
    List<User> getUsers(int theSortField);
    void deleteAnAccount(Integer userId);
    void updateUser(User updatedUser);
    User getUser(Integer userId);

    void updateCurrencyInDatabase(List<Currency> currency);
}
