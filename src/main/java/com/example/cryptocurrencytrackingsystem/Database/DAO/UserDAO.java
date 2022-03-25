package com.example.cryptocurrencytrackingsystem.Database.DAO;


import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.example.cryptocurrencytrackingsystem.Entity.Validation.CrmUser;

import java.util.List;

public interface UserDAO {
    List<Currency> getSortedCurrencies(int theSortField);
    User getUser(String login);
    void saveUser(CrmUser crmUser);
}
