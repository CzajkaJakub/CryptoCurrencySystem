package com.example.cryptocurrencytrackingsystem.Database.DAOS.User;


import com.example.cryptocurrencytrackingsystem.Entity.User;



public interface UserDAO {
    //user
    User getUser(String login);
    void saveUser(User user);


}
