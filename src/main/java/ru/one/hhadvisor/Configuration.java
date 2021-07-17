package ru.one.hhadvisor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import javax.sql.DataSource;


@org.springframework.context.annotation.Configuration
@ComponentScan("ru.one.hhadvisor")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories
public class Configuration {



}
