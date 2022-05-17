package com.example.cryptocurrencytrackingsystem.Aspects;

import com.example.cryptocurrencytrackingsystem.Database.Service.DataServiceInterface;
import com.example.cryptocurrencytrackingsystem.Entity.Statistic;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Aspect
@Component("statAspect")
public class StatAspect {

    private final DataServiceInterface dataService;

    @Autowired
    public StatAspect(@Qualifier("dataService") DataServiceInterface dataService) {
        this.dataService = dataService;
    }

    @Pointcut("execution(* com.example.cryptocurrencytrackingsystem.HomeControllerService.showDashboard(..))")
    public void mainPage(){}

    @Pointcut("execution(* com.example.cryptocurrencytrackingsystem.HomeControllerService.processForm(..))")
    public void registerPage(){}


    @Before("mainPage()")
    public void showMainPage(){
        updateStat(dataService.getStatistics("page_entry"));
    }

    @Before("registerPage()")
    public void showRegisterForm(){
        updateStat(dataService.getStatistics("register_number"));
    }

    public void updateStat(Statistic statistic){
        statistic.setValue(statistic.getValue() + 1);
        dataService.updateStatistics(statistic);
    }
}
