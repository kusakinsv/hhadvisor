package ru.one.hhadvisor;

import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.one.hhadvisor.program.TableCleaner;
import ru.one.hhadvisor.services.Parser;
import ru.one.hhadvisor.services.VacancyParser;


import javax.sql.DataSource;


@Configuration
@ComponentScan("ru.one.hhadvisor")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories
public class AppConfiguration {

@Bean
TableCleaner tableCleaner(){
    return new TableCleaner();
}

@Bean
@Scope("prototype")
VacancyParser vacancyParser(){
    return new VacancyParser();
}

}
