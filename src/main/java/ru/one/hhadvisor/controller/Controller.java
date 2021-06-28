package ru.one.hhadvisor.controller;

import net.minidev.json.JSONObject;
import org.hibernate.boot.model.relational.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import ru.one.hhadvisor.entity.repos.VacancyRepo;
import ru.one.hhadvisor.output.Vacancy;
import ru.one.hhadvisor.program.JsonAreas;
import ru.one.hhadvisor.program.TableCleaner;
import ru.one.hhadvisor.program.Vacancies;
import ru.one.hhadvisor.program.model.AreasFromString;
import ru.one.hhadvisor.services.EntityManagerImpl;
import ru.one.hhadvisor.services.VacancyParser;

import javax.management.Query;
import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("/")
public class Controller {


    private String url = "https://api.hh.ru/search";
    private final String testurl = "https://api.hh.ru/vacancies?per_page=4&page=22&text=Java";
    private final String hhurl = "https://api.hh.ru/vacancies";
    private final String page1 = "&per_page=20";
    private final String perpage1 = "&page=1";
    private final VacancyParser vacancyParser;

    @Autowired
    public Controller(VacancyParser vacancyParser) {
        this.vacancyParser = vacancyParser;
    }

    @Autowired
    private VacancyRepo vacancyRepo;

    //    @GetMapping //рабочий основной=====================
//    public List<Vacancy> searchParams(@RequestParam(value = "name", required = false) String name,
//                                      // @RequestParam(value = "per_page", required = false) Integer perPage,
//                                      @RequestParam(value = "count", required = false) Integer count
//    ) {
//
//        String queryUrl = "";
//        VacancyParser parser = new VacancyParser();
//        if (count==null) {return parser.doParse(name);} else {
//            System.out.println(queryUrl);
//            return parser.doParse(name, count);
//        }
//    }
//    //=================================================

//    @GetMapping("take")
//    public List<Vacancy> test() {
//
//        return StreamSupport.stream(vacancyRepo.findAll().spliterator(), false).collect(Collectors.toList());
//
//
////                StreamSupport
////                .stream(vacancyRepo.findAll().spliterator(), false)
////                .collect(Collectors.toList());
//    }

    @GetMapping("search") //  Погружение в БД
    public String searchParams(@RequestParam(value = "name", required = false) String name,
                                      // @RequestParam(value = "per_page", required = false) Integer perPage,
                                      @RequestParam(value = "count", required = false) Integer count,
                                      @RequestParam(value = "area", required = false) Integer area
    ) throws InterruptedException, SQLException {
        TableCleaner tc = new TableCleaner();
        tc.truncate("vacancy");
        VacancyParser parser = new VacancyParser();
//        JdbcTemplate template = new JdbcTemplate();
//        template.execute("TRUNCATE TABLE vacancy");
        if (count == null && area == null) {
            List<Vacancy> vacancies = parser.doParse(name);
            vacancyRepo.saveAll(vacancies);
            return "Complete";
        } else if (area == null) {
            List<Vacancy> vacancies = parser.doParse(name, count);
            vacancyRepo.saveAll(vacancies);
            return "Complete";
        } else {
            List<Vacancy> vacancies = parser.doParseWithAreas(name, count, area);
             vacancyRepo.saveAll(vacancies);
            System.out.println("DB operations complete");
            return "Complete";
        }
    }

    @GetMapping("take")
    public List<Vacancy> test() {
        return StreamSupport
                .stream(vacancyRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/hello")//тест
    public String list() {
        return "Hello";
    }

    @GetMapping("/vac")//тест
    public String vac(@RequestParam("name") String name) {
        return "first/hello";
    }

    @GetMapping("/areas")
    public ResponseEntity getAreasInJson() throws FileNotFoundException {
        JsonAreas jsonAreas = new JsonAreas();
        return ResponseEntity.ok(jsonAreas.toString());
    }

    @GetMapping("/areas2")
    public File getAreasInJson2() throws FileNotFoundException {
        JsonAreas jsonAreas = new JsonAreas();
        return new File("java/other/areas.json");
    }


    @RequestMapping("/out")//тест
    public List<Vacancy> getVacancies() {
        VacancyParser parser = new VacancyParser();
        return parser.doParse(testurl);
    }


    @RequestMapping("/name=Java")//тест
    public List<Vacancy> getString() {
        VacancyParser parser = new VacancyParser();
        return parser.doParse(testurl);
    }


    //тестовый для возврата
    public List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "First Message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "Second Message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "Third Message");
        }});
    }};


//    @GetMapping
//    public String searchParams(@RequestParam(value = "name", required = false) String name,
//                            @RequestParam(value = "per_page", required = false) Integer perPage,
//                            @RequestParam(value = "page", required = false) String page
//    ) {
//        System.out.println(name + perPage + page);
//        url = url + name + perPage + page;
//
//        return "Hello";//name + perPage + page;
//    }


}

//    @GetMapping
//    public String helloPage(@RequestParam(value = "username", required = false) String username,
//                            @RequestParam(value = "surname", required = false) String surname) {
//        System.out.println("Hello " + username + " " + surname);
//        return "helooworld";
//    }
//"https://api.hh.ru/vacancies?per_page=4&page=22&text=Java"


//    @GetMapping //пример запроса с параметрами
//    public String helloPage(HttpServletRequest request){
//           String usernamename = request.getParameter("username");
//           String surname = request.getParameter("surname");
//        System.out.println("Hello " + usernamename+ " " + surname);
//        return "helooworld";
//       }


//    @GetMapping("/v")
//    public ResponseEntity getNamedVacancies(){
//           return ResponseEntity.ok();
//    }


//        @Autowired
//        public Controller(Program vacRec) {
//            this.vacRec = vacRec;
//        }

//1, "Name", "Россия", 10, 20 ,"RUB", 33, 333)

//            @GetMapping("/{hello}")
//        public VacancyModel getVacancy() {
//            VacRec r = new VacRec();
//            r.testVacancyResponse();
//        return new VacancyModel();
//
//      }


//        @GetMapping
//        public ResponseEntity getAllVacancies() {
//           return ResponseEntity.ok(recipient.getAllVacancies().getItems());
//      }



