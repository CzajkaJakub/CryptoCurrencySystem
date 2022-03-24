package com.example.cryptocurrencytrackingsystem.Database.Service;


import com.example.cryptocurrencytrackingsystem.Database.DAO.AdminDAO;
import com.example.cryptocurrencytrackingsystem.Database.DAO.RoleDAO;
import com.example.cryptocurrencytrackingsystem.Database.DAO.UserDAO;
import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.Role;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.example.cryptocurrencytrackingsystem.Entity.Validation.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component("dataService")
public class DataService implements DataServiceInterface {

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private AdminDAO adminDAO;

    @Autowired
    public void setDAOs(@Qualifier("userDaoImpl") UserDAO userDAO,
                        @Qualifier("roleDaoImpl") RoleDAO roleDAO,
                        @Qualifier("adminDaoImpl") AdminDAO adminDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.adminDAO = adminDAO;
    }

    @Override
    @Transactional
    public List<Currency> getSortedCurrencies(int theSortField) {
        return userDAO.getSortedCurrencies(theSortField);
    }

    @Override
    @Transactional
    public User getUser(String login) {
        return userDAO.getUser(login);
    }

    @Override
    @Transactional
    public void saveUser(CrmUser crmUser) {
        userDAO.saveUser(crmUser);
    }

    @Override
    @Transactional
    public Role findRoleByName(String theRoleName) {
        return roleDAO.findRoleByName(theRoleName);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDAO.getUser(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }



    @Override
    @Transactional
    public List<User> getUsers(int theSortField) {return adminDAO.getUsers(theSortField);}



    @Override
    @Transactional
    public void deleteAnAccount(Integer userId) {
        adminDAO.deleteAnAccount(userId);
    }



    @Override
    @Transactional
    public User getUser(Integer userId) {
        return adminDAO.getUser(userId);
    }

}