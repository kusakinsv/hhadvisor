package ru.one.hhadvisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class HhadvisorApplication  {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(HhadvisorApplication.class, args);

		System.out.println("***********************");
		System.out.println("* Application Started *");
		System.out.println("***********************");



	}
}

//		VacancyParser parser = new VacancyParser();
//		parser.doParse();



//	public static Object nullableObj(Object z) {
//		if (z == null) return new NullableSalary();
//		else return z;	}
//
//	public static Object nullableString(Object z){
//		if (z == null) {
//			String g = "не указано";
//			return g;
//		}
//		return z;
//	}



		//		VacRec varvc = new VacRec();
//		varvc.testVacancyResponse();



