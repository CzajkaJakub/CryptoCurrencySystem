package com.example.cryptocurrencytrackingsystem.Database.DAO;


import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.example.cryptocurrencytrackingsystem.Entity.Validation.CrmUser;
import com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtils.SortUtilsCurrencies;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

@Repository
@Component("userDaoImpl")
public class UserDaoImpl implements UserDAO {

    private SessionFactory sessionFactory;
    private RoleDAO roleDAO;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setSessionFactoryDao(@Qualifier("sessionFactory")SessionFactory sessionFactory,
                                     @Qualifier("roleDaoImpl") RoleDAO roleDAO,
                                     @Qualifier("BCryptPasswordEncoder") BCryptPasswordEncoder passwordEncoder) {
        this.sessionFactory = sessionFactory;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Currency> getSortedCurrencies(int theSortField) {
        Session currentSession = sessionFactory.getCurrentSession();
        String sortBy;

        switch (theSortField) {
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
            case SortUtilsCurrencies.name_sort:
                sortBy = "name";
                break;
            default:
                sortBy = "market_cap_rank";
                //saveCurrenciesInDatabase();
                break;
        }

        String queryString = "from Currency order by " + sortBy;
        Query<Currency> theQuery = currentSession.createQuery(queryString, Currency.class);
        return theQuery.getResultList();
    }

    public void saveCurrenciesInDatabase(){
        Session session = sessionFactory.getCurrentSession();
        try {
            URL url = new URL("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false");
            ObjectMapper objectMapper = new ObjectMapper();
            List<?> list = objectMapper.readValue(url, new TypeReference<List<?>>() {});
            for (Object x: list) {
                String jsonCurrency = objectMapper.writeValueAsString(x);
                session.saveOrUpdate(objectMapper.readValue(jsonCurrency, Currency.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> theQuery = session.createQuery("from User where userName = :l", User.class);
        theQuery.setParameter("l", login);
        List<User> list = theQuery.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void saveUser(CrmUser crmUser) {
        Session session = sessionFactory.getCurrentSession();
        User user = new User(crmUser.getUserName(),
                passwordEncoder.encode(crmUser.getPassword()),
                crmUser.getFirstName(),
                crmUser.getLastName(),
                crmUser.getEmail());

        user.setRoles(Collections.singletonList(roleDAO.findRoleByName("ROLE_USER")));
        session.save(user);
    }
}
