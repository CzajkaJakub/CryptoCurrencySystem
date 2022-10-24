package com.example.cryptocurrencytrackingsystem.Configuration.ApplicationConfiguration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

    @Override
    protected Class< ? >[] getRootConfigClasses()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Class< ? >[] getServletConfigClasses()
    {
        return new Class[]
        { CryptoCurrencyTrackingSystemConfiguration.class };
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[]
        { "/" };
    }
}
