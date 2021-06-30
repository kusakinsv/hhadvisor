package ru.one.hhadvisor.services;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.one.hhadvisor.output.Vacancy;
import ru.one.hhadvisor.program.TableCleaner;
import ru.one.hhadvisor.program.exp.ModelForExperience;
import ru.one.hhadvisor.program.model.Models;
import ru.one.hhadvisor.program.threads.ThreadParser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacancyParser {
    private static int counter = 2000;
    //    public static RestTemplate restTemplate;
    public static final String mainurl = "https://api.hh.ru/vacancies";

    public static int countpages = 1; //default = 1
    public static String name;
    public static int area;
    public static int icount;
    public static int countProtector = 1;
    public static int leftover = 0;
    public static int round = 0;
    public static Models response;



    RestTemplate restTemplate = new RestTemplate();

    public List<Vacancy> doParseWithAreas(String name, int area) throws SQLException, InterruptedException {
        VacancyParser.area = area;
        VacancyParser.name = name;
        TableCleaner tc = new TableCleaner();
        tc.truncate("vacancy");
        System.out.println("Text:" + name + " || Count:" + counter + " || Area: " + area);
        if (counter > 2000) counter = 2000;
        String url = mainurl + "?per_page=" + 1 + "&page=" + 0 + "&text=" + name + "&area=" + area;
        Models responseFound = restTemplate.getForObject(url, Models.class);
        Integer foundItems = responseFound.getFound();
        System.out.println("Found: " + foundItems);
        if(foundItems < counter) counter = foundItems;
        List<Vacancy> parsedlist = new ArrayList<>();
        leftover = 0;
        icount = 20;
        if (counter <= 20) {countpages = 1;
            icount = counter;}
        if (counter > 20) {
            if ((counter%20) !=0) {
                leftover = counter % 20;
                countpages = (counter - leftover) / 20;
            } else {countpages = counter/20;}
        }
        System.out.println("Pages:" + countpages+1);//количество страниц
        System.out.println(url);
        ThreadParser.counterIDs = 1;
        int pagesCount = 0;
        countProtector = 1;


        for (int j = 0; j <= countpages; j++) {
            url = VacancyParser.mainurl + "?per_page=" + 20 + "&page=" + VacancyParser.round + "&text=" + VacancyParser.name + "&area=" + VacancyParser.area;
            response = restTemplate.getForObject(url, Models.class);
            round = j;
            ThreadParser tp = new ThreadParser();
            tp.run();
            pagesCount = j+1;
//            if (counterIDs >= 2000) break;
//            if (countProtector >= 2000) break;

        }
        while(ThreadParser.threadCounter !=countpages+1){
            Thread.sleep(100);

            System.out.println("счетчик потоков = " + ThreadParser.threadCounter);
            System.out.println("стр = " + countpages);

        }

        System.out.println("Search Complete");
        System.out.println("насчитал: " + ThreadParser.integercountVacancy);
        System.out.println("counterIDs: " + ThreadParser.counterIDs + "|| countProtector:" + countProtector);
        System.out.println("Cicles: "+ pagesCount + " || Items: " + ThreadParser.getListOfVacancies().size());
        //========возврат потоков на дефолтные значения - ThreadParser
        ThreadParser.threadCounter = 0;
        ThreadParser.counterIDs = 1;
        ThreadParser.integercountVacancy = 0;
        //===============================================
        countpages = 1; //возвращаем значение общей переменной
        icount = 20;
        countProtector = 1;
        leftover = 0;
        round = 0;

        //===============================================
       // System.out.println("Размер: " + ThreadParser.getListOfVacancies().size());
        return ThreadParser.getListOfVacancies();
    }

//=== раб осн
//    public List<Vacancy> doParseWithAreas(String name, int area) throws SQLException {
//        TableCleaner tc = new TableCleaner();
//        tc.truncate("vacancy");
//        System.out.println("Text:" + name + " || Count:" + counter + " || Area: " + area);
//        if (counter > 2000) counter = 2000;
//        String url = mainurl + "?per_page=" + 1 + "&page=" + 0 + "&text=" + name + "&area=" + area;
//        Models responseFound = restTemplate.getForObject(url, Models.class);
//        Integer foundItems = responseFound.getFound();
//        System.out.println("Found: " + foundItems);
//        if(foundItems < counter) counter = foundItems;
//        List<Vacancy> parsedlist = new ArrayList<>();
//        int leftover = 0;
//        int countpages = 1;
//        int icount = 20;
//        if (counter <= 20) {countpages = 1;
//            icount = counter;}
//        if (counter > 20) {
//            if ((counter%20) !=0) {
//                leftover = counter % 20;
//                countpages = (counter - leftover) / 20;
//            } else {countpages = counter/20;}
//        }
//        System.out.println("Pages:" + countpages+1);//количество страниц
//        System.out.println(url);
//        int counterIDs = 1;
//        int pagesCount = 0;
//        int countProtector = 1;
//        for (int j = 0; j <= countpages; j++) {
//
//            if (counterIDs >= 2000) break;
//            if (countProtector >= 2000) break;
//            pagesCount = j+1;
//            url = mainurl + "?per_page=" + 20 + "&page=" + j + "&text=" + name + "&area=" + area;
//            Models responseE = restTemplate.getForObject(url, Models.class);
//            Integer found = responseE.getFound();
//
//            if (j == countpages) icount = leftover;
//            for (int i = 0; i < icount; i++) {
//                countProtector++;
//                if (responseE.getItems().get(i).getSalary().getFrom() == null && responseE.getItems().get(i).getSalary().getTo() == null) continue;
//                if (!responseE.getItems().get(i).getSalary().getCurrency().equals("RUR")) continue;
//                parsedlist.add(new Vacancy(null, counterIDs,
//                        responseE.getItems().get(i).getName(),
//                        responseE.getItems().get(i).getEmployer().getName(),
//                        responseE.getItems().get(i).getArea().getName(),
//                        responseE.getItems().get(i).getSalary().getFrom(),
//                        responseE.getItems().get(i).getSalary().getTo(),
//                        responseE.getItems().get(i).getSalary().getCurrency(),
//                        Integer.parseInt(responseE.getItems().get(i).getArea().getId()),
//                        Integer.parseInt(responseE.getItems().get(i).getId()))
//                );
//                counterIDs++;
//                if (counterIDs > 2000) break;
//                if (countProtector > 2000) break;
//            }
//        }
//        System.out.println("Search Complete");
//        System.out.println("counterIDs: " + counterIDs + "|| countProtector:" + countProtector);
//        System.out.println("Cicles: "+ pagesCount + " || Items: "+parsedlist.size());
//        return parsedlist;
//    }


    public List<Vacancy> doParse(int area){
        System.out.println("Count:" + counter + " || Area: " + area);
        if (counter > 2000) counter = 2000;
        String url = mainurl + "?per_page=" + 1 + "&page=" + 0 + "&area=" + area;
        Models responseFound = restTemplate.getForObject(url, Models.class);
        Integer foundItems = responseFound.getFound();
        System.out.println("Found: " + foundItems);
        if(foundItems < counter) counter = foundItems;
        List<Vacancy> parsedlist = new ArrayList<>();
        int leftover = 0;
        int countpages = 1;
        int icount = 20;
        if (counter <= 20) {countpages = 1;
            icount = counter;}
        if (counter > 20) {
            if ((counter%20) !=0) {
                leftover = counter % 20;
                countpages = (counter - leftover) / 20;
            } else {countpages = counter/20;}
        }
        System.out.println("Pages:" + countpages);//количество страниц
        System.out.println(url);
        int counterIDs = 1;
        int pagesCount = 0;
        int countProtector = 1;
        for (int j = 0; j <= countpages; j++) {
            //============CountProtectors
            if (counterIDs >= 2000) break;
            if (countProtector >= 2000) break;
            //if (j > 99) break;
            //============================
            pagesCount = j+1;
            url = mainurl + "?per_page=" + 20 + "&page=" + j + "&area=" + area;
            Models response = restTemplate.getForObject(url, Models.class);

            Integer found = response.getFound();

            if (j == countpages) icount = leftover;
            for (int i = 0; i < icount; i++) {
                countProtector++;
                if (response.getItems().get(i).getSalary().getFrom() == null && response.getItems().get(i).getSalary().getTo() == null) continue;
                if (!response.getItems().get(i).getSalary().getCurrency().equals("RUR")) continue;
                parsedlist.add(new Vacancy(null, counterIDs,
                        response.getItems().get(i).getName(),
                        response.getItems().get(i).getArea().getName(),
                        response.getItems().get(i).getEmployer().getName(),
                        response.getItems().get(i).getSalary().getFrom(),
                        response.getItems().get(i).getSalary().getTo(),
                        response.getItems().get(i).getSalary().getCurrency(),
                        null,
                        null,
                        Integer.parseInt(response.getItems().get(i).getArea().getId()),
                        Integer.parseInt(response.getItems().get(i).getId()))
                );
                counterIDs++;
                if (counterIDs > 2000) break;
                if (countProtector > 2000) break;
            }
        }
        System.out.println("Search Complete");
        System.out.println("counterIDs: " + counterIDs + "|| countProtector:" + countProtector);
        System.out.println("Cicles: "+ pagesCount + " || Items: "+parsedlist.size());
        return parsedlist;
    }

    public List<Vacancy> doParse(String name){
        System.out.println("Text:" + name + " || Count:" + counter);
        if (counter > 2000) counter = 2000;
        String url = mainurl + "?per_page=" + 1 + "&page=" + 0 + "&text=" + name;
        Models responseFound = restTemplate.getForObject(url, Models.class);
        Integer foundItems = responseFound.getFound();
        System.out.println("Found: " + foundItems);
        if(foundItems < counter) counter = foundItems;
        List<Vacancy> parsedlist = new ArrayList<>();
        int leftover = 0;
        int countpages = 1;
        int icount = 20;
        if (counter <= 20) {countpages = 1;
            icount = counter;}
        if (counter > 20) {
            if ((counter%20) !=0) {
                leftover = counter % 20;
                countpages = (counter - leftover) / 20;
            } else {countpages = counter/20;}
        }
        System.out.println("Pages:" + countpages);//количество страниц
        System.out.println(url);
        int counterIDs = 1;
        int pagesCount = 0;
        int countProtector = 1;
        for (int j = 0; j <= countpages; j++) {
            //============CountProtectors
            if (counterIDs >= 2000) break;
            if (countProtector >= 2000) break;
            //if (j > 99) break;
            //============================
            pagesCount = j+1;
            url = mainurl + "?per_page=" + 20 + "&page=" + j + "&text=" + name;
            Models response = restTemplate.getForObject(url, Models.class);
            Integer found = response.getFound();

            if (j == countpages) icount = leftover;
            for (int i = 0; i < icount; i++) {
                countProtector++;
                if (response.getItems().get(i).getSalary().getFrom() == null && response.getItems().get(i).getSalary().getTo() == null) continue;
                if (!response.getItems().get(i).getSalary().getCurrency().equals("RUR")) continue;
                parsedlist.add(new Vacancy(null, counterIDs,
                        response.getItems().get(i).getName(),
                        response.getItems().get(i).getArea().getName(),
                        response.getItems().get(i).getEmployer().getName(),
                        response.getItems().get(i).getSalary().getFrom(),
                        response.getItems().get(i).getSalary().getTo(),
                        response.getItems().get(i).getSalary().getCurrency(),
                        null,
                        null,
                        Integer.parseInt(response.getItems().get(i).getArea().getId()),
                        Integer.parseInt(response.getItems().get(i).getId()))
                );
                counterIDs++;
                if (counterIDs > 2000) break;
                if (countProtector > 2000) break;
            }
        }
        System.out.println("Search Complete");
        System.out.println("counterIDs: " + counterIDs + "|| countProtector:" + countProtector);
        System.out.println("Cicles: "+ pagesCount + " || Items: "+parsedlist.size());
        return ThreadParser.listOfVacancies;
    }
}

