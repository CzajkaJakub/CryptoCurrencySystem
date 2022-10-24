package com.example.cryptocurrencytrackingsystem.Database.DAO;

import java.util.List;

import com.example.cryptocurrencytrackingsystem.Entity.User;

public interface AdminDAO
{
    List< User > getUsers( int theSortField );

    void deleteAnAccount( Integer userId );
}