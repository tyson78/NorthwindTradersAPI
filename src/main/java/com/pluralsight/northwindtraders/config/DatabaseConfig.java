package com.pluralsight.northwindtraders.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// DatabaseConfig creates a dataSource bean that is injectable
@Configuration
public class DatabaseConfig {

    private BasicDataSource basicDataSource;
    private Logger logger;

    @Bean // @bean means this dataSource method(returns basicDataSource) can be injected somewhere else
    // dataSource bean is usually injected to DAO classes
    public DataSource dataSource(){
        return basicDataSource;
    }

    @Autowired
    public DatabaseConfig (@Value("${datasource.url}") String url,
                           @Value("${datasource.username}") String username,
                           @Value("${datasource.password}") String password)
    {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        logger = LoggerFactory.getLogger(this.getClass());

        logger.info("Created data source");
    }
}