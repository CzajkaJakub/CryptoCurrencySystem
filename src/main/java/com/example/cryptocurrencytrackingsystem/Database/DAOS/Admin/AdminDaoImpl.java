package com.example.cryptocurrencytrackingsystem.Database.DAOS.Admin;

import com.example.cryptocurrencytrackingsystem.Entity.User;
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
@Component("adminDaoImpl")
public class AdminDaoImpl implements AdminDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public AdminDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getAdminAccount(User admin) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, 1);
    }


    @Override
    public List<User> getUsers(int theSortField){
        Session currentSession = sessionFactory.getCurrentSession();
        String sortBy = "id";

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
    public void deleteAnAccount(Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery("delete from User where id= :userId");
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

}
