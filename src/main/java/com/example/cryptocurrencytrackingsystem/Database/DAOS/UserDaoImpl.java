package com.example.cryptocurrencytrackingsystem.Database.DAOS;

import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtilsCurrencies;
import com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtilsUsers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Repository
@Component("userDaoImpl")
public class UserDaoImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUser(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> theQuery = session.createQuery("from User where login = :l", User.class);
        theQuery.setParameter("l", login);
        List<User> list = theQuery.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public User getAdminAccount() {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, 1);
    }

    @Override
    public List<User> getUsers(int theSortField){
        Session currentSession = sessionFactory.getCurrentSession();
        String sortBy = null;

        switch (theSortField) {
            case SortUtilsUsers.id_sort:
                sortBy = "id";
                break;
            case SortUtilsUsers.login_sort:
                sortBy = "login";
                break;
            case SortUtilsUsers.password_sort:
                sortBy = "password";
                break;
            case SortUtilsUsers.email_sort:
                sortBy = "email";
        }

        String queryString = "from User order by " + sortBy;
        Query<User> theQuery = currentSession.createQuery(queryString, User.class);
        return theQuery.getResultList();
    }

    @Override
    public void deleteAnAccount(Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery("delete from User where id= :userId");
        query.setParameter("userId", userId);
        query.executeUpdate();
    }


    @Override
    public void updateUser(User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        session.update(updatedUser);
    }

    @Override
    public User getUser(Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, userId);
    }

    @Override
    public void updateCurrencyInDatabase(List<Currency> currency) {
        Session session = sessionFactory.getCurrentSession();
        for (Currency curr: currency) {
            try{
                session.save(currency);
            } catch (Exception e){
                session.update(curr);
            }
        }
    }

    @Override
    public List<Currency> getSortedCurrencies(int theSortField) {
        Session currentSession = sessionFactory.getCurrentSession();
        String sortBy = null;

        switch (theSortField) {
            case SortUtilsCurrencies.name_sort:
                sortBy = "name";
                break;
            case SortUtilsCurrencies.symbol_sort:
                sortBy = "symbol";
                break;
            case SortUtilsCurrencies.current_price_sort:
                sortBy = "current_price";
                break;
            case SortUtilsCurrencies.market_cap_sort:
                sortBy = "market_cap";
                break;
            case SortUtilsCurrencies.market_cap_rank_sort:
                sortBy = "market_cap_rank";
                break;
            case SortUtilsCurrencies.ath_sort:
                sortBy = "ath";
                break;
            case SortUtilsCurrencies.atl_sort:
                sortBy = "atl";
                break;
            case SortUtilsCurrencies.high_24h_sort:
                sortBy = "high_24h";
                break;
            case SortUtilsCurrencies.low_24h_sort:
                sortBy = "low_24h";
                break;
        }

        String queryString = "from Currency order by " + sortBy;
        Query<Currency> theQuery = currentSession.createQuery(queryString, Currency.class);
        return theQuery.getResultList();
    }


}
