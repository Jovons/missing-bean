package com.example.demo.config;
// https://javatodev.com/multiple-datasources-with-spring-boot-data-jpa/
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

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.repository.bird",
        entityManagerFactoryRef = "birdEntityManagerFactory",
        transactionManagerRef= "birdTransactionManager")
public class BirdDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.bird")
    public DataSourceProperties cardDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.bird.configuration")
    public DataSource cardDataSource() {
        return cardDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "birdEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean birdEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(cardDataSource())
                .packages("com.example.demo.entity.bird")
                .build();
    }

    @Bean
    public PlatformTransactionManager birdTransactionManager(
            final @Qualifier("birdEntityManagerFactory") LocalContainerEntityManagerFactoryBean birdEntityManagerFactory) {
        return new JpaTransactionManager(birdEntityManagerFactory.getObject());
    }

}
