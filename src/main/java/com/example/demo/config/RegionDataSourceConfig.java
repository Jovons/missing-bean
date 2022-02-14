package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.repository.region",
        entityManagerFactoryRef = "regionEntityManagerFactory",
        transactionManagerRef= "regionTransactionManager")
public class RegionDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.region")
    public DataSourceProperties regionDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.region.configuration")
    public DataSource regionDataSource() {
        return regionDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "regionEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean regionEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(regionDataSource())
                .packages("com.example.demo.entity.region")
                .build();
    }

}
