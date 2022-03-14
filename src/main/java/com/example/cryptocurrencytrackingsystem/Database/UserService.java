package com.example.cryptocurrencytrackingsystem.Database;

import com.example.cryptocurrencytrackingsystem.Database.DAOS.UserDAO;
import com.example.cryptocurrencytrackingsystem.Database.DAOS.UserDaoImpl;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

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
}
