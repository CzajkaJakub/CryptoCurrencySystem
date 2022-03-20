package com.example.cryptocurrencytrackingsystem.Configuration.ApplicationConfiguration;

import com.example.cryptocurrencytrackingsystem.Configuration.ApplicationConfiguration.CryptoCurrencyTrackingSystemConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class
DispatcherServerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {CryptoCurrencyTrackingSystemConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
