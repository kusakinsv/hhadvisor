package ru.one.hhadvisor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.one.hhadvisor.entity.repos.VacancyRepo;
import ru.one.hhadvisor.output.Vacancy;
import ru.one.hhadvisor.program.JsonAreas;
import ru.one.hhadvisor.program.threads.ThreadSaver;
import ru.one.hhadvisor.services.VacancyParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
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
    public VacancyRepo vacancyRepo;

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

    @GetMapping("search") //  Погружение в БД
    public HashMap<String, String> searchParams(@RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "area", required = false) Integer area
    ) throws SQLException, InterruptedException {

        VacancyParser parser = new VacancyParser();
        if (name == null && area == null) {
            System.out.println("Error: no parameters");
            return new HashMap<String, String>() {{
                put("system", "no parameters");
            }};
        } else if (area == null) {
            List<Vacancy> vacancies = parser.doParse(name);
            // vacancyRepo.saveAll(vacancies);
            System.out.println("DB operations complete");
            return new HashMap<String, String>() {{
                put("system", "operation complete");
            }};
        } else if (name == null) {
            List<Vacancy> vacancies = parser.doParse(area);
            //  vacancyRepo.saveAll(vacancies);
            System.out.println("DB operations complete");
            return new HashMap<String, String>() {{
                put("system", "operation complete");
            }};
        } else {
            List<Vacancy> vacancies = parser.doParseWithAreas(name, area);
            System.out.println("DB write operations....");
            vacancyRepo.saveAll(vacancies);
            //DBWriter.toWrite(vacancies);
            System.out.println("TOTAL " + ThreadSaver.vacancyListFoeDBSaver.size());
            System.out.println("DB operations complete");
            return new HashMap<String, String>() {{
                put("system", "operation complete");
            }};}
    }


    @GetMapping("take") //================Спрятал на время теста
    public List<Vacancy> take() {
        return StreamSupport
                .stream(vacancyRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

//    @GetMapping("areas")
//    public String
//     test() throws IOException {
//        JsonAreas jsonAreas = new JsonAreas();
//        return jsonAreas.testTakeAreas();
//    }

    @RequestMapping("test")
    public  List<Map<String, String>> testResponse(){
        List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
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
        return messages;
    }
    @GetMapping("/areas2")
    public File getAreasInJson2() throws FileNotFoundException {
        JsonAreas jsonAreas = new JsonAreas();
        return new File("java/other/areas.json");
    }
    @RequestMapping("/name=Java")//тест
    public List<Vacancy> getString() {
        VacancyParser parser = new VacancyParser();
        return parser.doParse(testurl);
    }
}

//    @GetMapping("/areas")
//    public ResponseEntity getAreasInJson() throws FileNotFoundException {
//        JsonAreas jsonAreas = new JsonAreas();
//        return ResponseEntity.ok("classpath:areas.json");
//    }



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




//"https://api.hh.ru/vacancies?per_page=4&page=22&text=Java"


//    @GetMapping //пример запроса с параметрами
//    public String helloPage(HttpServletRequest request){
//           String usernamename = request.getParameter("username");
//           String surname = request.getParameter("surname");
//        System.out.println("Hello " + usernamename+ " " + surname);
//        return "helooworld";
//       }






