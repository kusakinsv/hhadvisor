package ru.one.hhadvisor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.one.hhadvisor.repositories.VacancyRepo;
import ru.one.hhadvisor.entity.Vacancy;
import ru.one.hhadvisor.program.statistics.MinMaxStat;
import ru.one.hhadvisor.services.VacancyParser;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("/")
public class Controller {
    public static boolean inProgress = false;

    @Autowired
    private VacancyParser vacancyParser;

    @Autowired
    public Controller(VacancyParser vacancyParser) {
        this.vacancyParser = vacancyParser;
    }

    @Autowired
    public VacancyRepo vacancyRepo;

    @GetMapping("search")
    public ResponseEntity searchParams(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "area", required = false) String area
    ) throws SQLException, InterruptedException {
        if (inProgress) return ResponseEntity.ok(new HashMap<String, String>() {{
            put("system", "previous request in progress");
        }});
        inProgress = true;
        restoreDefaults();
        if (name == null && area == null) {
            System.out.println("Error: no parameters");
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("system", "no parameters");
            }});
        } else if (area == null) {
            List<Vacancy> vacancies = vacancyParser.doParseOnlyName(name);
            return ResponseEntity.ok(makeStatistics(vacancies));
        } else if (name == null) {
            List<Vacancy> vacancies = vacancyParser.doParseOnlyArea(area);
            return ResponseEntity.ok(makeStatistics(vacancies));
        } else {
            List<Vacancy> vacancies = vacancyParser.doParseWithAreas(name, area);
            return ResponseEntity.ok(makeStatistics(vacancies));
        }
    }

    MinMaxStat makeStatistics(List<Vacancy> vacancies){
        MinMaxStat stat = new MinMaxStat();
        System.out.println("DB write operations....");
        vacancies.parallelStream().forEach(x -> vacancyRepo.save(x));
        //vacancyRepo.saveAll(vacancies); старый метод
        System.out.println("DB operations complete");
        if (vacancies.size() > 1) stat.doStat(vacancies);
        inProgress = false;
        return stat;
    }

    @GetMapping("take") //================Спрятал на время теста
    public List<Vacancy> take() {
        return StreamSupport
                .stream(vacancyRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void restoreDefaults() {
        VacancyParser.unionvaclist.clear();
        VacancyParser.countpages = 1; //возвращаем значение общей переменной
        VacancyParser.countProtector = 1;
        VacancyParser.leftover = 0;
    }

    @GetMapping(value = "test")
    ResponseEntity<?> test() {
        return new ResponseEntity<>(HttpStatus.valueOf(200), HttpStatus.OK);
    }

}







