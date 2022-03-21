package com.example.cryptocurrencytrackingsystem.Database.DAOS.Common;

import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtils.SortUtilsCurrencies;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component("commonDaoImpl")
public class CommonDaoImpl implements CommonDAO{

    private final SessionFactory sessionFactory;

    @Autowired
    public CommonDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Currency> getSortedCurrencies(int theSortField) {
        Session currentSession = sessionFactory.getCurrentSession();
        String sortBy = "name";

        switch (theSortField) {
            case SortUtilsCurrencies.symbol_sort:
                sortBy = "symbol";
                break;
            case SortUtilsCurrencies.current_price_sort:
                sortBy = "current_price";
                break;
            case SortUtilsCurrencies.market_cap_sort:
                sortBy = "market_cap";
                break;
            case SortUtilsCurrencies.market_cap_rank_sort:
                sortBy = "market_cap_rank";
                break;
            case SortUtilsCurrencies.ath_sort:
                sortBy = "ath";
                break;
            case SortUtilsCurrencies.atl_sort:
                sortBy = "atl";
                break;
            case SortUtilsCurrencies.high_24h_sort:
                sortBy = "high_24h";
                break;
            case SortUtilsCurrencies.low_24h_sort:
                sortBy = "low_24h";
                break;
            case SortUtilsCurrencies.name_sort:
                sortBy = "name";
                break;
        }

        String queryString = "from Currency order by " + sortBy;
        Query<Currency> theQuery = currentSession.createQuery(queryString, Currency.class);
        System.out.println(theQuery.getResultList().size());
        return theQuery.getResultList();
    }
}
