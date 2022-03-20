package com.example.cryptocurrencytrackingsystem.Database.DAOS.Admin;

import com.example.cryptocurrencytrackingsystem.Entity.User;

import java.util.List;

public interface AdminDAO {
    User getAdminAccount(User admin);
    List<User> getUsers(int theSortField);
    void deleteAnAccount(Integer userId);
    void updateUser(User updatedUser);
    User getUser(Integer userId);
}