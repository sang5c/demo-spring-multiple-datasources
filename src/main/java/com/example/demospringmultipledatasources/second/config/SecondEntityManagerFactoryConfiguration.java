package com.example.demospringmultipledatasources.second.config;

import com.example.demospringmultipledatasources.second.repository.Second;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration(proxyBeanMethods = false)
public class SecondEntityManagerFactoryConfiguration {

    /**
     * JpaProperties가 이미 빈으로 등록되어 있다.
     * 변경이 필요한 경우만 아래와 같은 형식으로 빈을 재정의 한다.
     */
    // @Bean
    // @ConfigurationProperties("spring.jpa")
    // public JpaProperties firstJpaProperties() {
    //     return new JpaProperties();
    // }

    // TODO: EntityManagerFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(DataSource secondDataSource, JpaProperties jpaProperties) {
        EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(jpaProperties);
        return builder.dataSource(secondDataSource)
                .packages(Second.class)
                .persistenceUnit("secondDs")
                .build();
    }

    // TODO: 설명
    private EntityManagerFactoryBuilder createEntityManagerFactoryBuilder(JpaProperties jpaProperties) {
        JpaVendorAdapter jpaVendorAdapter = createJpaVendorAdapter(jpaProperties);
        return new EntityManagerFactoryBuilder(jpaVendorAdapter, jpaProperties.getProperties(), null);
    }

    // TODO: 설명
    private JpaVendorAdapter createJpaVendorAdapter(JpaProperties jpaProperties) {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
        hibernateJpaVendorAdapter.setShowSql(jpaProperties.isShowSql());
        hibernateJpaVendorAdapter.setGenerateDdl(jpaProperties.isGenerateDdl());
        hibernateJpaVendorAdapter.setDatabase(jpaProperties.getDatabase());
        return hibernateJpaVendorAdapter;
    }

    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager(LocalContainerEntityManagerFactoryBean secondEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(secondEntityManagerFactory.getObject()));
    }

}

