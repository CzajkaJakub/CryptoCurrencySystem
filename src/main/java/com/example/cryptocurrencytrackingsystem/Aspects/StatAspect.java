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
    public void processRegisterForm(){}


    @Before("mainPage()")
    public void showMainPageStatistics(){updateStat("page_entry");}

    @Before("processRegisterForm()")
    public void registerProcesses(){
        updateStat("register_number");
    }

    public void updateStat(String id){
        Statistic statistic = dataService.getStatistics(id);
        statistic.setValue(statistic.getValue() + 1);
        dataService.updateStatistics(statistic);
    }
}
