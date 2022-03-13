package com.example.portfolio.Database.DAOS;

import com.example.portfolio.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();
    void saveUser(User user);
    User getAdminAccount();
    User getUser(String login);
    User getUser(Integer id);
    void deleteAnAccount(Integer userId);
    void updateUser(User updatedUser);

}
