package com.example.demospringmultipledatasources.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration(proxyBeanMethods = false)
public class DataSourcesConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    // @Primary
    // @Bean
    // @ConfigurationProperties("app.datasource.first.configuration")
    // public HikariDataSource firstDataSource(DataSourceProperties firstDataSourceProperties) {
    //     return firstDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    // }

    @Bean
    @ConfigurationProperties("spring.second-datasource")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }

    // @Bean
    // @ConfigurationProperties("app.datasource.second.configuration")
    // public HikariDataSource secondDataSource(
    //         @Qualifier("secondDataSourceProperties") DataSourceProperties secondDataSourceProperties) {
    //     return secondDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    // }

}

