package com.example.cryptocurrencytrackingsystem.Database.DAOS.Common;

import com.example.cryptocurrencytrackingsystem.Entity.Currency;

import java.util.List;

public interface CommonDAO {
    List<Currency> getSortedCurrencies(int theSortField);
}
