package ru.one.hhadvisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.one.hhadvisor.program.TableCleaner;

import java.io.IOException;

@SpringBootApplication
public class HhadvisorApplication  {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = SpringApplication.run(HhadvisorApplication.class, args);
		System.out.println("***********************");
		System.out.println("* Application Started *");
		System.out.println("***********************");
	}
}
