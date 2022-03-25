//package com.example.cryptocurrencytrackingsystem.Aspects;
//
//import com.example.cryptocurrencytrackingsystem.Entity.Currency;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.aspectj.lang.annotation.*;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//import java.io.IOException;
//import java.net.URL;
//import java.util.List;
//
//@Aspect
//@Component("currenciesAspect")
//public class CurrenciesAspect {
//
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public CurrenciesAspect(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Pointcut("execution(* com.example.cryptocurrencytrackingsystem.Database.Service.DataService.loadUserByUsername(..))")
//    public void updateCurrencies(){}
//
//    @Transactional
//    @AfterReturning("updateCurrencies()")
//    public void saveCurrenciesInDatabase(){
//        Session session = sessionFactory.getCurrentSession();
//        try {
//            URL url = new URL("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false");
//            ObjectMapper objectMapper = new ObjectMapper();
//            List<?> list = objectMapper.readValue(url, new TypeReference<List<?>>() {});
//            for (Object x: list) {
//                String jsonCurrency = objectMapper.writeValueAsString(x);
//                session.saveOrUpdate(objectMapper.readValue(jsonCurrency, Currency.class));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}