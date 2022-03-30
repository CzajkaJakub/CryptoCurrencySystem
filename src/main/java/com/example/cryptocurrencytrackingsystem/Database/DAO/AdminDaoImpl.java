package com.example.cryptocurrencytrackingsystem.Database.DAO;

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
    public List<User> getUsers(int theSortField){
        Session currentSession = sessionFactory.getCurrentSession();
        String sortBy;

        switch (theSortField) {
            case SortUtilsUsers.username_sort:
                sortBy = "username";
                break;
            case SortUtilsUsers.first_name_sort:
                sortBy = "first_name";
                break;
            case SortUtilsUsers.last_name_sort:
                sortBy = "last_name";
                break;
            case SortUtilsUsers.email_sort:
                sortBy = "email";
                break;

            case SortUtilsUsers.id_sort:
            default:
                sortBy = "id";
                break;
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

}
