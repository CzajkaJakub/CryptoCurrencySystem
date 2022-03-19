package com.example.cryptocurrencytrackingsystem.Database.DAOS.User;

import com.example.cryptocurrencytrackingsystem.Database.DAOS.User.UserDAO;
import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtils.SortUtilsCurrencies;
import com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtils.SortUtilsUsers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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



}
