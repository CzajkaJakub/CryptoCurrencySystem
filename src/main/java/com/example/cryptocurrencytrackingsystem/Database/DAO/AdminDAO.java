package com.example.cryptocurrencytrackingsystem.Database.DAO;

import com.example.cryptocurrencytrackingsystem.Entity.User;

import java.util.List;

public interface AdminDAO {
    List<User> getUsers(int theSortField);
    void deleteAnAccount(Integer userId);
}