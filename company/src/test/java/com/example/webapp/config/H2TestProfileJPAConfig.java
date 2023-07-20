//package com.example.webapp.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableJpaRepositories(basePackages = {
//        "com.example.webapp.repository"
//})
//@EnableTransactionManagement
//public class H2TestProfileJPAConfig {
//    @Bean
//    @Profile("playground")
//    public DataSource playgroundDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:mem:testdb/playground");
////        dataSource.setUsername("sa");
////        dataSource.setPassword("sa");
//
//        return dataSource;
//    }
//
//    @Bean
//    @Profile("account")
//    public DataSource accountDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:mem:testdb/account");
////        dataSource.setUsername("sa");
////        dataSource.setPassword("sa");
//
//        return dataSource;
//    }
//
//}
