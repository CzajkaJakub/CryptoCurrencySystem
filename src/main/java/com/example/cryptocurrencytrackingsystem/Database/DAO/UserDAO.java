package com.example.cryptocurrencytrackingsystem.Database.DAO;

import java.util.List;

import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.Entity.Statistic;
import com.example.cryptocurrencytrackingsystem.Entity.User;
import com.example.cryptocurrencytrackingsystem.Entity.Validation.CrmUser;

public interface UserDAO
{
    List< Currency > getSortedCurrencies( int theSortField, Integer pageNumber );

    User getUser( String login );

    void saveUser( CrmUser crmUser );

    void updateUser( User user );

    Statistic getStatistics( String statID );

    void updateStatistics( Statistic statistic );
}
