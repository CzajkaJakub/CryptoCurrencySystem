package com.example.cryptocurrencytrackingsystem.Database.DAO;


import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.example.cryptocurrencytrackingsystem.Entity.Validation.CrmUser;
import com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtils.SortUtilsCurrencies;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


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
                sortBy = "symbol asc";
                break;
            case SortUtilsCurrencies.current_price_sort:
                sortBy = "current_price desc";
                break;
            case SortUtilsCurrencies.market_cap_rank_sort:
                sortBy = "market_cap_rank asc";
                break;
            case SortUtilsCurrencies.ath_sort:
                sortBy = "ath desc";
                break;
            case SortUtilsCurrencies.atl_sort:
                sortBy = "atl desc";
                break;
            case SortUtilsCurrencies.high_24h_sort:
                sortBy = "high_24h desc";
                break;
            case SortUtilsCurrencies.low_24h_sort:
                sortBy = "low_24h desc";
                break;
            case SortUtilsCurrencies.name_sort:
                sortBy = "name asc";
                break;
            case SortUtilsCurrencies.market_cap_sort:
            default:
                sortBy = "market_cap desc";
                break;
        }


        String queryString = "from Currency order by " + sortBy;
        Query<Currency> theQuery = currentSession.createQuery(queryString, Currency.class);
        return theQuery.getResultList();
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

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }
}
