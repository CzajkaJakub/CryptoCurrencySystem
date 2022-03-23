package com.example.cryptocurrencytrackingsystem.Database.Service;

import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.Role;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.example.cryptocurrencytrackingsystem.Entity.Validation.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface DataServiceInterface extends UserDetailsService {
    //user
    List<Currency> getSortedCurrencies(int theSortField);
    User getUser(String login);
    void saveUser(CrmUser crmUser);
    Role findRoleByName(String theRoleName);

    //admin
    List<User> getUsers(int theSortField);
    void deleteAnAccount(Integer userId);
    User getUser(Integer userId);

}
