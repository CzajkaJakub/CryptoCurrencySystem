package com.example.cryptocurrencytrackingsystem.Database.DAOS;

import com.example.cryptocurrencytrackingsystem.Entity.User;
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

    @Override
    public User getAdminAccount() {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, 1);
    }

    @Override
    public List<User> getUsers(){
        Session session = sessionFactory.getCurrentSession();
        Query<User> theQuery = session.createQuery("from User order by login", User.class);
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
}
