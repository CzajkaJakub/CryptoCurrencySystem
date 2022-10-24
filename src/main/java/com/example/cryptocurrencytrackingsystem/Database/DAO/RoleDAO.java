package com.example.cryptocurrencytrackingsystem.Database.DAO;

import com.example.cryptocurrencytrackingsystem.Entity.Role;

public interface RoleDAO
{
    Role findRoleByName( String theRoleName );
}
