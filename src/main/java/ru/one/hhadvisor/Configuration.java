package ru.one.hhadvisor;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


@org.springframework.context.annotation.Configuration
@ComponentScan("ru.one.hhadvisor")
@PropertySource("classpath:application.properties")
public class Configuration {

}
