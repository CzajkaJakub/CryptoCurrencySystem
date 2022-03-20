package com.example.cryptocurrencytrackingsystem.Database;

import com.example.cryptocurrencytrackingsystem.Database.DAOS.Admin.AdminDAO;
import com.example.cryptocurrencytrackingsystem.Database.DAOS.Admin.AdminDaoImpl;
import com.example.cryptocurrencytrackingsystem.Database.DAOS.Common.CommonDAO;
import com.example.cryptocurrencytrackingsystem.Database.DAOS.Common.CommonDaoImpl;
import com.example.cryptocurrencytrackingsystem.Database.DAOS.User.UserDAO;
import com.example.cryptocurrencytrackingsystem.Database.DAOS.User.UserDaoImpl;
import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
@Component("dataService")
public class DataService implements DataServiceInterface {

    private UserDAO userDAO;
    private CommonDAO commonDAO;
    private AdminDAO adminDao;

    @Autowired
    public void setDAOs(@Qualifier("userDaoImpl") UserDAO userDAO,
                        @Qualifier("adminDaoImpl") AdminDAO adminDAO,
                        @Qualifier("commonDaoImpl") CommonDAO commonDAO) {
        this.userDAO = userDAO;
        this.adminDao = adminDAO;
        this.commonDAO = commonDAO;
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
    public User getAdminAccount(User admin) {
        return adminDao.getAdminAccount(admin);
    }

    @Override
    @Transactional
    public List<User> getUsers(int theSortField) {return adminDao.getUsers(theSortField);}

    @Override
    @Transactional
    public void deleteAnAccount(Integer userId) {
        adminDao.deleteAnAccount(userId);
    }

    @Override
    @Transactional
    public void updateUser(User updatedUser) {
        adminDao.updateUser(updatedUser);
    }

    @Override
    @Transactional
    public User getUser(Integer userId) {
        return adminDao.getUser(userId);
    }

    @Override
    @Transactional
    public List<Currency> getSortedCurrencies(int theSortField) {
        return commonDAO.getSortedCurrencies(theSortField);
    }


}
