package com.example.cryptocurrencytrackingsystem.Aspects;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.cryptocurrencytrackingsystem.Entity.Currency;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component( "currenciesAspect" )
public class CurrenciesAspect
{

    private final SessionFactory sessionFactory;

    @Autowired
    public CurrenciesAspect( @Qualifier( "sessionFactory" ) SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }

    @Pointcut( "execution(* com.example.cryptocurrencytrackingsystem.Database.Service.DataService.getSortedCurrencies(..))" )
    public void updateCurrencies()
    {
    }

    @Before( "updateCurrencies()" )
    public void saveCurrenciesInDatabase( JoinPoint joinPoint )
    {
        Object[] signatureArgs = joinPoint.getArgs();
        int pageNumber = ((Integer)signatureArgs[ 1 ]) + 1;
        Session session = sessionFactory.getCurrentSession();

        try
        {
            URL url = new URL(
                "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=50&page="
                    + pageNumber + "&sparkline=false" );
            ObjectMapper objectMapper = new ObjectMapper();
            List< ? > list = objectMapper.readValue( url, new TypeReference< List< ? > >()
            {} );
            for( Object x : list )
            {
                String jsonCurrency = objectMapper.writeValueAsString( x );
                session.saveOrUpdate( objectMapper.readValue( jsonCurrency, Currency.class ) );
            }
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }
}