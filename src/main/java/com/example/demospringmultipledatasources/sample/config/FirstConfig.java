package com.example.demospringmultipledatasources.sample.config;

import com.example.demospringmultipledatasources.sample.repository.Sample;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(basePackageClasses = Sample.class, entityManagerFactoryRef = "entityManagerFactory")
public class FirstConfig {

}


