```bash
docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Pass@word" \
  -p 1433:1433 --name mssql -h mssql \
  -d mcr.microsoft.com/mssql/server:2019-latest
```

## Multiple DataSources

구성을 위해 필요한 키워드는 다음과 같습니다.

- DataSource
- JpaProperties
- TransactionManager
- EntityManagerFactoryBuilder

### Custom DataSource!
```java
@Configuration
public class JpaPropertiesConfig {
  @Bean
  @ConfigurationProperties("spring.jpa")
  public JpaProperties jpaProperties() {
    return new JpaProperties();
  }

  @Bean
  @ConfigurationProperties("spring.jpa.hibernate")
  public HibernateProperties hibernateProperties() {
    return new HibernateProperties();
  }
}
```
- 2개 이상의 datasource 사용시 위 bean들이 자동 설정되지 않는다.

```java

@Configuration(proxyBeanMethods = false)
public class MyDataSourceConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
    
    @Bean
    @ConfigurationProperties("spring.second-datasource")
    public HikariDataSource secondDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
```

- `spring.datasource`로 시작하는 properties를 읽어 DataSource를 생성합니다.
    - 읽어오는 값은 DataSourceProperties 클래스를 통해 확인 가능합니다.
- type을 지정하지 않으면 아래 목록중 가능한 DataSource를 찾아서 생성합니다.
    - Hikari (com.zaxxer.hikari.HikariDataSource)
    - Tomcat JDBC Pool (org.apache.tomcat.jdbc.pool.DataSource)
    - Apache DBCP2 (org.apache.commons.dbcp2.BasicDataSource)
    - Oracle UCP (oracle.ucp.jdbc.PoolDataSourceImpl)
- DataSource Bean을 수동으로 등록하면 자동설정이 무시되어 기본으로 사용하던 DataSource도 수동으로 등록해야 합니다.

### TransactionManager, EntityManagerFactoryBuilder
```java
@Configuration(proxyBeanMethods = false)
public class FirstEntityManagerFactoryConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("spring.jpa")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean firstEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(firstEntityManagerFactory.getObject()));
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(DataSource firstDataSource, JpaProperties jpaProperties) {
        EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(jpaProperties);
        return builder.dataSource(firstDataSource)
                .packages(Sample.class)
                .persistenceUnit("firstDs")
                .build();
    }

    private EntityManagerFactoryBuilder createEntityManagerFactoryBuilder(JpaProperties jpaProperties) {
        JpaVendorAdapter jpaVendorAdapter = createJpaVendorAdapter(jpaProperties);
        return new EntityManagerFactoryBuilder(jpaVendorAdapter, jpaProperties.getProperties(), null);
    }

    private JpaVendorAdapter createJpaVendorAdapter(JpaProperties jpaProperties) {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
        hibernateJpaVendorAdapter.setShowSql(jpaProperties.isShowSql());
        hibernateJpaVendorAdapter.setGenerateDdl(jpaProperties.isGenerateDdl());
        hibernateJpaVendorAdapter.setDatabase(jpaProperties.getDatabase());
        return hibernateJpaVendorAdapter;
    }
}

```

## 참고 - Spring Boot Docs

- [Configure a Custom DataSource](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto.data-access.configure-custom-datasource)
- [Configure Two DataSources](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto.data-access.configure-two-datasources)
- [Using Multiple EntityManagerFactories](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto.data-access.use-multiple-entity-managers)
