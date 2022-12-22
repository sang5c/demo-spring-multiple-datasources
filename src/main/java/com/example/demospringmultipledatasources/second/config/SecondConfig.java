package com.example.demospringmultipledatasources.second.config;

import com.example.demospringmultipledatasources.sample.repository.Sample;
import com.example.demospringmultipledatasources.second.repository.Second;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(
        basePackageClasses = Second.class,
        entityManagerFactoryRef = "secondEntityManagerFactory"
)
public class SecondConfig {

}


