package ru.one.hhadvisor;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.util.ClassUtils;
import ru.one.hhadvisor.program.DBWriter;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;


@org.springframework.context.annotation.Configuration
@ComponentScan("ru.one.hhadvisor")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories
public class Configuration {
    @Bean
    public DBWriter writerBean(){
        return new DBWriter();
    }



}
