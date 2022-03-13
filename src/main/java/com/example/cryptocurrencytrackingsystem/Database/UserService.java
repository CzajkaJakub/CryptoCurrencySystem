package com.example.cryptocurrencytrackingsystem.Database;

import com.example.portfolio.Database.DAOS.UserDAO;
import com.example.portfolio.Database.DAOS.UserDaoImpl;
import com.example.portfolio.entity.User;
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
    public List<User> getUsers() {
        return userDAO.getUsers();
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
    public User getUser(String login) {
        return userDAO.getUser(login);
    }

    @Override
    @Transactional
    public void deleteAnAccount(Integer userId) {
        userDAO.deleteAnAccount(userId);
    }

    @Override
    @Transactional
    public User getUser(Integer id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void updateUser(User updatedUser) {
        userDAO.updateUser(updatedUser);
    }
}
