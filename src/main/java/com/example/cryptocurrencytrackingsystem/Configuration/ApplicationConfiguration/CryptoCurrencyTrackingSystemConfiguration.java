package com.example.cryptocurrencytrackingsystem.Configuration.ApplicationConfiguration;

import org.hibernate.SessionFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example.cryptocurrencytrackingsystem")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource("classpath:persistenceDatabase.properties")
public class CryptoCurrencyTrackingSystemConfiguration implements WebMvcConfigurer {

    private final org.springframework.core.env.Environment environment;

    @Autowired
    public CryptoCurrencyTrackingSystemConfiguration(org.springframework.core.env.Environment environment) {
        this.environment = environment;
    }


    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean(name = "myDataSource")
    public DataSource comboPooledDataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        comboPooledDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        comboPooledDataSource.setUser(environment.getProperty("jdbc.user"));
        comboPooledDataSource.setPassword(environment.getProperty("jdbc.password"));
        comboPooledDataSource.setInitialPoolSize(getIntPropert("connection.pool.initialPoolSize"));
        comboPooledDataSource.setMinPoolSize(getIntPropert("connection.pool.minPoolSize"));
        comboPooledDataSource.setMaxPoolSize(getIntPropert("connection.pool.maxPoolSize"));
        comboPooledDataSource.setMaxIdleTime(getIntPropert("connection.pool.maxIdleTime"));
        return comboPooledDataSource;
    }

    private int getIntPropert(String propName){
        String propVal = environment.getProperty(propName);
        assert propVal != null;
        return Integer.parseInt(propVal);
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory(@Qualifier("myDataSource") DataSource myDataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        // set the properties
        localSessionFactoryBean.setDataSource(myDataSource);
        localSessionFactoryBean.setPackagesToScan(environment.getProperty("hiberante.packagesToScan"));
        localSessionFactoryBean.setHibernateProperties(getHibernateProperties());
        return localSessionFactoryBean;
    }

    private Properties getHibernateProperties() {

        // set hibernate properties
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        return props;
    }

    @Autowired
    @Bean(name = "myTransactionManager")
    public HibernateTransactionManager hibernateTransactionManager(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }

}
