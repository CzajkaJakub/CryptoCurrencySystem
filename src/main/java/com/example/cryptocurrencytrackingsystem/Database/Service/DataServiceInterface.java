package com.example.cryptocurrencytrackingsystem.Database.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.Statistic;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.example.cryptocurrencytrackingsystem.Entity.UserAddress;
import com.example.cryptocurrencytrackingsystem.Entity.Validation.CrmUser;

public interface DataServiceInterface extends UserDetailsService
{
    // user
    List< Currency > getSortedCurrencies( int theSortField, Integer pageNumber );

    User getUser( String login );

    void saveUser( CrmUser crmUser );

    void updateUserAddress( String login, UserAddress userAddress );

    Statistic getStatistics( String statID );

    void updateStatistics( Statistic statistic );

    // admin
    List< User > getUsers( int theSortField );

    void deleteAnAccount( Integer userId );

}
