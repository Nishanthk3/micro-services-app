package com.example.webapp.persistenceConfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.webapp.repository.account",
        entityManagerFactoryRef = "accountEntityManagerFactory",
        transactionManagerRef = "accountTransactionManager")
public class AccountPersistenceConfiguration {

    @Bean
    //@Primary // should not use primary annotation for this persistence config, there is only one default config,
    // and in our case it is Playground and PlaygroundPersistenceConfiguration will have @primary
    @ConfigurationProperties("spring.datasource.account")
    public DataSourceProperties accountDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    //@Primary
    @ConfigurationProperties("spring.datasource.account.configuration") // these settings are for any hikari connection poll settings, timeout's , other db connection related settings
    public DataSource accountDataSource() {
        return accountDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    //@Primary
    @Bean(name = "accountEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(accountDataSource())
                .persistenceUnit("account")
                .packages(new String[]{"com.example.webapp.entity.account"})
                .build();
    }

    //@Primary
    @Bean
    public PlatformTransactionManager accountTransactionManager(
            final @Qualifier("accountEntityManagerFactory") LocalContainerEntityManagerFactoryBean accountEntityManagerFactory) {
        return new JpaTransactionManager(accountEntityManagerFactory.getObject());
    }
}
