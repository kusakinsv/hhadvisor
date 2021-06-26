package ru.one.hhadvisor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.one.hhadvisor.output.Vacancy;
import ru.one.hhadvisor.services.VacancyParser;

import java.util.List;


@RestController
@RequestMapping("/search")
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


    @GetMapping("/hello")
    public String list() {
        return "Hello";
    }

    @GetMapping("/vac")
    public String vac(@RequestParam("name") String name) {
        return "first/hello";
    }



    //        @GetMapping("/{vacName}")
//        public ResponseEntity getVacancyById (@PathVariable String vacName){
//            return ResponseEntity.ok(vacancyParser.getVacancyById(vacName));
//        }
    @RequestMapping("/out")
    public List<Vacancy> getVacancies() {
        VacancyParser parser = new VacancyParser();
        return parser.doParse(testurl);
    }


    @RequestMapping("/name=Java")
    public List<Vacancy> getString() {
        VacancyParser parser = new VacancyParser();
        return parser.doParse(testurl);
    }

    @GetMapping
    public List<Vacancy> searchParams(@RequestParam(value = "name", required = false) String name,
                              // @RequestParam(value = "per_page", required = false) Integer perPage,
                               @RequestParam(value = "count", required = false) Integer count
    ) {
        System.out.println("Text:" + name + " || Count:" + count);
        String queryUrl = "";
       // queryUrl = hhurl + "?per_page=" + 20 + "&page=0" + page + "&text=" + name;
        VacancyParser parser = new VacancyParser();
        if (count==null) {return parser.doParse(name);} else {
        System.out.println(queryUrl);
        return parser.doParse(name, count);
    }}



    //    public List<Vacancy> getParams(String url) {
//        VacancyParser parser = new VacancyParser();
//        return parser.doParse(url);
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

//        @PostMapping
//        public ResponseEntity getAllVacancies(@RequestBody SearchParametersVacancy params) {
//            return ResponseEntity.ok(vacRec.getAllVacancies(params).getItems());
//        }

//        @GetMapping("/{vacId}")
//        public ResponseEntity getVacancyById(@PathVariable String vacId) {
//            return ResponseEntity.ok(vacRec.getVacancyById(vacId));
//        }
//    @GetMapping("/{vacId}")
//    public ResponseEntity getVacancyById(@PathVariable String vacId) {
//        return ResponseEntity.ok(vacRec.getVacancyById(vacId));
//    }


//   @GetMapping("/{name}")
//      public ResponseEntity getVacancyByName(@PathVariable String name) {
//       return ResponseEntity.ok(vacRec.getVacancyByName(name));
// }

    /*@GetMapping("/{vacName}")
    public ResponseEntity getVacancyByName(@PathVariable String vacName) {
        return ResponseEntity.ok(vacRec.getVacancyByName(vacName));
    }*/



