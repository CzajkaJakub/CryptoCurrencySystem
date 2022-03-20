package com.example.cryptocurrencytrackingsystem.Aspects;


import com.example.cryptocurrencytrackingsystem.Entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component("adminAspect")
public class AdminAspects {

    @Pointcut("execution(* com.example.cryptocurrencytrackingsystem.Database.DAOS.Admin.AdminDAO.getAdminAccount(..))")
    public void checkAdminData(){}

    @Around("checkAdminData()")
    public User checkAdminData(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
        User loginData = (User)theProceedingJoinPoint.getArgs()[0];
        User adminAccount = (User)theProceedingJoinPoint.proceed();
        if(adminAccount.equals(loginData)){return adminAccount;}
        return null;
    }
}
