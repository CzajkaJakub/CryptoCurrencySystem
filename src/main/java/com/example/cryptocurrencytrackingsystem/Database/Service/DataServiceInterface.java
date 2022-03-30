package com.example.cryptocurrencytrackingsystem.Database.Service;

import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.example.cryptocurrencytrackingsystem.Entity.UserAddress;
import com.example.cryptocurrencytrackingsystem.Entity.Validation.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface DataServiceInterface extends UserDetailsService {
    //user
    List<Currency> getSortedCurrencies(int theSortField);
    List<Currency> getSortedCurrencies();
    User getUser(String login);
    void saveUser(CrmUser crmUser);
    void updateUserAddress(String login, UserAddress userAddress);

    //admin
    List<User> getUsers(int theSortField);
    void deleteAnAccount(Integer userId);


}
