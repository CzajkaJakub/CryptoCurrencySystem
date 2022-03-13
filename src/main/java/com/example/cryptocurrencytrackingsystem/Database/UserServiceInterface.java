package com.example.cryptocurrencytrackingsystem.Database;

import com.example.portfolio.entity.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getUsers();
    void saveUser(User user);
    User getAdminAccount();
    User getUser(String login);
    void deleteAnAccount(Integer userId);
    User getUser(Integer id);
    void updateUser(User updatedUser);
}
