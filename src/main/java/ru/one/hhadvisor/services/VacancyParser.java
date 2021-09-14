package ru.one.hhadvisor.services;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.one.hhadvisor.output.Vacancy;
import ru.one.hhadvisor.program.TableCleaner;
import ru.one.hhadvisor.program.models.model.Models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Getter
@Service
public class VacancyParser {

    private final int perPage = 100;
    public int counter = 2000;
    public static final String mainurl = "https://api.hh.ru/vacancies";
    public static int countpages = 1; //default = 1
    public static int countProtector = 1;
    public static int leftover = 0;
    public static int round = 0;
    public static List<Vacancy> unionvaclist = Collections.synchronizedList(new ArrayList<>());

    @Autowired
    public TableCleaner tableCleaner;


    RestTemplate restTemplate = new RestTemplate();

    public VacancyParser() {
    }

    public int countVacanciesOnPage(int foundItems){

        return 0;
    }


    public void startParsing(String[] url, String foundUrl) throws SQLException, InterruptedException {
        tableCleaner.truncate();
        int foundItems = foundItems(foundUrl);
        if (counter > 2000) counter = 2000;
        if(foundItems < counter) counter = foundItems;
        leftover = 0;
        int count = perPage;
        if (counter <= perPage) {countpages = 1;
            count = counter;}
        if (counter > perPage) {
            if ((counter%perPage) !=0) {
                leftover = counter % perPage;
                countpages = (counter - leftover) / perPage;
            } else {countpages = counter/perPage;}
        }
        System.out.println("Страниц:" + countpages);//количество страниц
        System.out.println("Количество на страницу:" + count);//количество на страницу
        System.out.println(url);
        Parser.counterIDs = 1;
        int pagesCount = 0;
        countProtector = 1;
        System.out.println();
        List<Thread> listofThreads = new ArrayList<>();
        for (int j = 0; j <= countpages; j++) {
            if (foundItems == 0) break;
            Models response = restTemplate.getForObject(url[0] + j + url[1], Models.class);
            round = j;
            Thread tp = new Thread(new Parser(response, j, count));
            if (j < countpages){
                listofThreads.add(tp);
                tp.start();
            } else {
                listofThreads.add(tp);
                tp.start();
                for (Thread x : listofThreads
                ) {
                    x.join();
                }
            }
            pagesCount = j+1;
        }

        System.out.println("Search Complete");
        System.out.println("насчитал: " + Parser.integercountVacancy);
        System.out.println("counterIDs: " + Parser.counterIDs + "|| countProtector:" + countProtector);
        System.out.println("Cicles: "+ (pagesCount-1) + " || Items: " + unionvaclist.size());//+ ThreadParser.getListOfVacancies().size());
        //========возврат потоков на дефолтные значения - ThreadParser
        Parser.counterIDs = 1;
        Parser.integercountVacancy = 0;
        //===============================================
        countpages = 1; //возвращаем значение общей переменной
        count = perPage;
        countProtector = 1;
        leftover = 0;
        round = 0;
    }

    public int foundItems(String url){
        Models responseFound = restTemplate.getForObject(url, Models.class);
        System.out.println("Found: " + responseFound.getFound());
        return responseFound.getFound();

    }


    public List<Vacancy> doParseWithAreas(String name, String area) throws SQLException, InterruptedException {
        System.out.println("Text:" + name + " || Count:" + counter + " || Area: " + area);
        String foundUrl = mainurl + "?per_page=" + 1 + "&page=" + 0 + "&text=" + name + "&area=" + area;
        String[] url = new String[]{mainurl + "?per_page=" + perPage + "&page=", "&text=" + name + "&area=" + area};
        startParsing(url, foundUrl);
        return unionvaclist;
    }

    public List<Vacancy> doParseWithArea(String area) throws InterruptedException, SQLException {
        System.out.println("Count:" + counter + " || Area: " + area);
        String foundUrl = mainurl + "?per_page=" + 1 + "&page=" + 0 + "&area=" + area;
        String[] url = new String[]{mainurl + "?per_page=" + perPage + "&page=", "&area=" + area};
        startParsing(url, foundUrl);
        return unionvaclist;
       }

    public List<Vacancy> doParseWithName(String name) throws InterruptedException, SQLException {
        String foundUrl = mainurl + "?per_page=" + 1 + "&page=" + 0 + "&text=" + name;
        String[] url = new String[]{mainurl + "?per_page=" + perPage + "&page=", "&text=" + name};
        startParsing(url, foundUrl);
        return unionvaclist;
    }




    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}

