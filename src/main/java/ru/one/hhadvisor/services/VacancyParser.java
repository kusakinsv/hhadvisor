package ru.one.hhadvisor.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.one.hhadvisor.output.Vacancy;
import ru.one.hhadvisor.program.model.Models;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacancyParser {

    RestTemplate restTemplate = new RestTemplate();
    private static final String mainurl = "https://api.hh.ru/vacancies";

    public List<Vacancy> doParse(String name, int counter) {
        counter = 580;
        Integer maxCount = 20;
        Integer count = 5;
        if (count > 20) count = maxCount;



        String url = mainurl + "?per_page=" + count + "&page=" + 0 + "&text=" + name;
        System.out.println(url);
        List<Vacancy> parsedlist = new ArrayList<>();
        Models responseE = restTemplate.getForObject(url, Models.class);
        for (int i = 0; i < 4; i++) {
            parsedlist.add(new Vacancy(i,
                    responseE.getItems().get(i).getName(),
                    responseE.getItems().get(i).getArea().getName(),
                    responseE.getItems().get(i).getSalary().getFrom(),
                    responseE.getItems().get(i).getSalary().getTo(),
                    responseE.getItems().get(i).getSalary().getCurrency(),
                    Integer.parseInt(responseE.getItems().get(i).getArea().getId()),
                    responseE.getItems().get(i).getId())
            );
        }

        return parsedlist;
    }

    public List<Vacancy> doParse(String url) {//перегруженный метод
        Integer pageCount = 0;

        List<Vacancy> parsedlist = new ArrayList<>();
        Models responseE = restTemplate.getForObject(url, Models.class);
        for (int i = 0; i < 5; i++) {
            parsedlist.add(new Vacancy(i,
                    responseE.getItems().get(i).getName(),
                    responseE.getItems().get(i).getArea().getName(),
                    responseE.getItems().get(i).getSalary().getFrom(),
                    responseE.getItems().get(i).getSalary().getTo(),
                    responseE.getItems().get(i).getSalary().getCurrency(),
                    Integer.parseInt(responseE.getItems().get(i).getArea().getId()),
                    responseE.getItems().get(i).getId())
            );
        }
        return parsedlist;
    }

//    public List<Vacancy> doParse(String url) {//рабочий метод
//        Integer pageCount = 0;
//
//        List<Vacancy> parsedlist = new ArrayList<>();
//        Models responseE = restTemplate.getForObject(url, Models.class);
//        for (int i = 0; i < 2; i++) {
//            parsedlist.add(new Vacancy(i,
//                    responseE.getItems().get(i).getName(),
//                    responseE.getItems().get(i).getArea().getName(),
//                    responseE.getItems().get(i).getSalary().getFrom(),
//                    responseE.getItems().get(i).getSalary().getTo(),
//                    responseE.getItems().get(i).getSalary().getCurrency(),
//                    Integer.parseInt(responseE.getItems().get(i).getArea().getId()),
//                    responseE.getItems().get(i).getId())
//            );
//        }
//        return parsedlist;
//    }

}


//Models vacancyModel = restTemplate.getForObject("https://api.hh.ru/vacancies?per_page=1&page=10&text=Java", Models.class);
//ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://api.hh.ru/vacancies?per_page=1&page=10&text=Java",String.class);

//System.out.println(vacancyModel.getItems().get(0).getName());
//    public List<Vacancy> doParse() {
//        List<Vacancy> parsedlist = new ArrayList<>();
//        for (int i = 0; i < 2; i++) {
//            Models responseE = restTemplate.getForObject("https://api.hh.ru/vacancies?per_page=20&page=55&text=Java", Models.class);
////        System.out.println(responseE.getItems().get(i).getId() + ", " +
////                responseE.getItems().get(i).getName() + ", " +
////                responseE.getItems().get(i).getArea().getId() + ", " +
////                responseE.getItems().get(i).getArea().getName() + " " +
////                responseE.getItems().get(i).getSalary().getFrom() + " " +
////                responseE.getItems().get(i).getSalary().getTo() + " " +
////                responseE.getItems().get(i).getSalary().getCurrency());
//
//            parsedlist.add(new Vacancy(i,
//                    responseE.getItems().get(i).getName(),
//                    responseE.getItems().get(i).getArea().getName(),
//                    responseE.getItems().get(i).getSalary().getFrom(),
//                    responseE.getItems().get(i).getSalary().getTo(),
//                    responseE.getItems().get(i).getSalary().getCurrency(),
//                    Integer.parseInt(responseE.getItems().get(i).getArea().getId()),
//                    responseE.getItems().get(i).getId())
//            );
////            System.out.println(parsedlist.get(i).getName() + " " + parsedlist.get(i).getUniqueId() );
//
//        }
////        public Vacancy getVacancyById() {                                                                              //работает запрос по id
////            return restTemplate.getForEntity("https://api.hh.ru/vacancies?per_page=10&page=55&text=Java", Vacancy.class).getBody();
////        }
//
////        public Vacancy getVacancyById(String id) {                                                                              //работает запрос по id
////            return restTemplate.getForEntity(hhApi + "/" + id, Vacancy.class).getBody();
////        }
//        return parsedlist;
//    }
//