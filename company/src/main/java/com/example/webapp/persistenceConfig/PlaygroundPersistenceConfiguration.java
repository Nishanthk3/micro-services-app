package com.example.webapp.persistenceConfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.webapp.repository.playground",
        entityManagerFactoryRef = "playgroundEntityManagerFactory",
        transactionManagerRef = "playgroundTransactionManager")
public class PlaygroundPersistenceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.playground")
    public DataSourceProperties playgroundDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.playground.configuration") // these settings are for any hikari connection poll settings, timeout's , other db connection related settings
    public DataSource playgroundDataSource() {
        return playgroundDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "playgroundEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean playgroundEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(playgroundDataSource())
                .persistenceUnit("playground")
                .packages(new String[] {"com.example.webapp.entity.playground"})
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager playgroundTransactionManager(
            final @Qualifier("playgroundEntityManagerFactory") LocalContainerEntityManagerFactoryBean playgroundEntityManagerFactory) {
        return new JpaTransactionManager(playgroundEntityManagerFactory.getObject());
    }
}
