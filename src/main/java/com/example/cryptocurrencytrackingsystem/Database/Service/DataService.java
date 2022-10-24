package com.example.cryptocurrencytrackingsystem.Database.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cryptocurrencytrackingsystem.Database.DAO.AdminDAO;
import com.example.cryptocurrencytrackingsystem.Database.DAO.UserDAO;
import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.Role;
import com.example.cryptocurrencytrackingsystem.Entity.Statistic;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.example.cryptocurrencytrackingsystem.Entity.UserAddress;
import com.example.cryptocurrencytrackingsystem.Entity.Validation.CrmUser;

@Service
@Component( "dataService" )
public class DataService implements DataServiceInterface
{

    private UserDAO userDAO;
    private AdminDAO adminDAO;

    @Autowired
    public void setDAOs( @Qualifier( "userDaoImpl" ) UserDAO userDAO,
        @Qualifier( "adminDaoImpl" ) AdminDAO adminDAO )
    {
        this.userDAO = userDAO;
        this.adminDAO = adminDAO;
    }

    @Override
    @Transactional
    public List< Currency > getSortedCurrencies( int theSortField, Integer pageNumber )
    {
        return userDAO.getSortedCurrencies( theSortField, pageNumber );
    }

    @Override
    @Transactional
    public User getUser( String login )
    {
        return userDAO.getUser( login );
    }

    @Override
    @Transactional
    public void saveUser( CrmUser crmUser )
    {
        userDAO.saveUser( crmUser );
    }

    @Override
    @Transactional
    public void updateUserAddress( String login, UserAddress userAddress )
    {
        User user = userDAO.getUser( login );
        userAddress.setUser( user );
        Set< UserAddress > userAddresses = user.getUserAddresses();
        userAddresses.add( userAddress );
        userDAO.updateUser( user );
    }

    @Override
    @Transactional
    public Statistic getStatistics( String statID )
    {
        return userDAO.getStatistics( statID );
    }

    @Override
    @Transactional
    public void updateStatistics( Statistic statistic )
    {
        userDAO.updateStatistics( statistic );
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername( String userName ) throws UsernameNotFoundException
    {
        User user = userDAO.getUser( userName );
        if( user == null )
        {
            throw new UsernameNotFoundException( "Invalid username or password." );
        }
        return new org.springframework.security.core.userdetails.User( user.getUserName(), user.getPassword(),
            mapRolesToAuthorities( user.getRoles() ) );
    }

    private Collection< ? extends GrantedAuthority > mapRolesToAuthorities( Collection< Role > roles )
    {
        return roles.stream()
            .map( role -> new SimpleGrantedAuthority( role.getName() ) )
            .collect( Collectors.toList() );
    }

    @Override
    @Transactional
    public List< User > getUsers( int theSortField )
    {
        return adminDAO.getUsers( theSortField );
    }

    @Override
    @Transactional
    public void deleteAnAccount( Integer userId )
    {
        adminDAO.deleteAnAccount( userId );
    }
}
