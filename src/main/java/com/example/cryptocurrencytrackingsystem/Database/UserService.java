package com.example.cryptocurrencytrackingsystem.Database;

import com.example.cryptocurrencytrackingsystem.Database.DAOS.UserDAO;
import com.example.cryptocurrencytrackingsystem.Database.DAOS.UserDaoImpl;
import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@Component("userService")
public class UserService implements UserServiceInterface {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(@Qualifier("userDaoImpl") UserDaoImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public User getUser(String login) {
        return userDAO.getUser(login);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public User getAdminAccount() {
        return userDAO.getAdminAccount();
    }

    @Override
    @Transactional
    public List<User> getUsers(int theSortField) {return userDAO.getUsers(theSortField);}

    @Override
    @Transactional
    public void deleteAnAccount(Integer userId) {
        userDAO.deleteAnAccount(userId);
    }

    @Override
    @Transactional
    public void updateUser(User updatedUser) {
        userDAO.updateUser(updatedUser);
    }

    @Override
    @Transactional
    public User getUser(Integer userId) {
        return userDAO.getUser(userId);
    }

    @Override
    @Transactional
    public List<Currency> getCurrencies() {
        List<Currency> currencyList = new ArrayList<>();
        try {
            URL url = new URL("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false");
            ObjectMapper objectMapper = new ObjectMapper();
            List<?> list = objectMapper.readValue(url, new TypeReference<List<?>>() {});
            for (Object x: list) {
                String jsonCurrency = objectMapper.writeValueAsString(x);
                Currency currency = objectMapper.readValue(jsonCurrency, Currency.class);
                currencyList.add(currency);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateCurrencyInDatabase(currencyList);
        return currencyList;
    }

    @Override
    @Transactional
    public void updateCurrencyInDatabase(List<Currency> currency) {
        userDAO.updateCurrencyInDatabase(currency);
    }

    @Override
    @Transactional
    public List<Currency> getSortedCurrencies(int theSortField) {
        return userDAO.getSortedCurrencies(theSortField);
    }


}
