package ru.one.hhadvisor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.one.hhadvisor.entity.repos.VacancyRepo;
import ru.one.hhadvisor.output.Vacancy;
import ru.one.hhadvisor.program.statistics.MinMaxStat;
import ru.one.hhadvisor.services.VacancyParser;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("/")
public class Controller {


//    private String url = "https://api.hh.ru/search";
//    private final String testurl = "https://api.hh.ru/vacancies?per_page=4&page=22&text=Java";
//    private final String hhurl = "https://api.hh.ru/vacancies";
//    private final String page1 = "&per_page=20";
//    private final String perpage1 = "&page=1";

 private final VacancyParser vacancyParser;

    @Autowired
    public Controller(VacancyParser vacancyParser) {
        this.vacancyParser = vacancyParser;
    }
    @Autowired
    public VacancyRepo vacancyRepo;


    @GetMapping("search") //  Погружение в БД
    public ResponseEntity searchParams(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "area", required = false) String area
    ) throws SQLException, InterruptedException {
        VacancyParser parser = new VacancyParser();
        MinMaxStat stat = new MinMaxStat();
        boolean b;
        if (name == null && area == null) {
            System.out.println("Error: no parameters");
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("system", "no parameters");
            }});
        } else if (area == null) {
            List<Vacancy> vacancies = parser.doParseWithName(name);

            System.out.println("DB write operations....");
            stat.doStat(vacancies);
            vacancyRepo.saveAll(vacancies);
            System.out.println("DB operations complete");
            return ResponseEntity.ok(stat);
        } else if (name == null) {
            List<Vacancy> vacancies = parser.doParseWithArea(area);
            stat.doStat(vacancies);
            System.out.println("DB write operations....");
            vacancyRepo.saveAll(vacancies);
            System.out.println("DB operations complete");
            return ResponseEntity.ok(stat);
        } else {
            List<Vacancy> vacancies = parser.doParseWithAreas(name, area);
            stat.doStat(vacancies);
            System.out.println("DB write operations....");
            vacancyRepo.saveAll(vacancies);
            //DBWriter.toWrite(vacancies);
            //System.out.println("TOTAL " + ThreadSaver.vacancyListFoeDBSaver.size());
            System.out.println("DB operations complete");
            return ResponseEntity.ok(stat);
           }
    }


       @GetMapping("take") //================Спрятал на время теста
    public List<Vacancy> take() {
        return StreamSupport
                .stream(vacancyRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}







