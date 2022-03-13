package com.example.cryptocurrencytrackingsystem.Database.DAOS;

import com.example.portfolio.entity.User;
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
    public List<User> getUsers(){

        // get a current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // create a query - HQL language
        Query<User> theQuery = session.createQuery("from User order by login", User.class);

        // return executable result
        return theQuery.getResultList();
    }

    @Override
    public void saveUser(User user) {
        // get a current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // save the object
        session.save(user);
    }

    @Override
    public User getAdminAccount() {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, 1);
    }


    @Override
    public User getUser(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> theQuery = session.createQuery("from User", User.class);
        User tempUser = null;
        for (User us: theQuery.getResultList()) {
            if(us.getLogin().equals(login)){
                tempUser = us;
            }
        }
        return tempUser;
    }

    @Override
    public User getUser(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public void deleteAnAccount(Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from User where id= :userIde");
        query.setParameter("userIde", userId);

    }

    @Override
    public void updateUser(User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        session.update(updatedUser);
    }
}
