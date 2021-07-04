package ru.one.hhadvisor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.one.hhadvisor.program.DBWriter;


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
