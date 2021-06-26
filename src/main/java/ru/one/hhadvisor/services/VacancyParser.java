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
        String url = mainurl + "?per_page=" + 1 + "&page=" + 0 + "&text=" + name;
        Models responseFound = restTemplate.getForObject(url, Models.class);
        Integer foundItems = responseFound.getFound();
        System.out.println("Found: " + foundItems);

        List<Vacancy> parsedlist = new ArrayList<>();
        int ostatok = 0;
        int countpages = 1;
        int searchPages;
        int searchvalues;
        int icount = 20;
        if (counter <= 20) {countpages = 1;
            icount = counter;}
        if (counter > 20) {
            if ((counter%20) !=0) {
            ostatok = counter % 20;
            countpages = (counter - ostatok) / 20;
        } else {countpages = counter/20;}
        }
        System.out.println("Pages:" + countpages);//количество страниц
        System.out.println(url);
        int counterIDs = 1;
        int pagesCount = 0;
        for (int j = 0; j <= countpages; j++) {

            if (counterIDs > 2000) break;
            pagesCount = j;
            url = mainurl + "?per_page=" + 20 + "&page=" + j + "&text=" + name;
            Models responseE = restTemplate.getForObject(url, Models.class);
            Integer found = responseE.getFound();

            if (j == countpages) icount = ostatok;
                        for (int i = 0; i < icount; i++) {
                parsedlist.add(new Vacancy(counterIDs,
                        responseE.getItems().get(i).getName(),
                        responseE.getItems().get(i).getArea().getName(),
                        responseE.getItems().get(i).getSalary().getFrom(),
                        responseE.getItems().get(i).getSalary().getTo(),
                        responseE.getItems().get(i).getSalary().getCurrency(),
                        Integer.parseInt(responseE.getItems().get(i).getArea().getId()),
                        responseE.getItems().get(i).getId())

                );
                            counterIDs++;
                            if (counterIDs > 2000) break;
            }
        }
        System.out.println("Поиск завершен");
        System.out.println("Cicles: "+ pagesCount + " || Items: "+parsedlist.size());
        return parsedlist;
    }

    public List<Vacancy> doParse(String url) {//перегруженный метод
        Integer pageCount = 0;

        List<Vacancy> parsedlist = new ArrayList<>();
        Models responseE = restTemplate.getForObject(url, Models.class);
        for (int i = 0; i < 1; i++) {
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