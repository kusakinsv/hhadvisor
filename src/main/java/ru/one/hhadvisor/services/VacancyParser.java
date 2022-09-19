package ru.one.hhadvisor.services;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import ru.one.hhadvisor.entity.Vacancy;
import ru.one.hhadvisor.program.TableCleaner;
import ru.one.hhadvisor.program.models.model.Models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Getter
public class VacancyParser {

    private final int perPage = 100;
    public int optionalCounter = 2000;

    private static final String mainurl = "https://api.hh.ru/vacancies";
    public static int countpages = 1; //default = 1
    public static int countProtector = 1;
    public static int leftover = 0;

    public static List<Vacancy> unionvaclist = Collections.synchronizedList(new ArrayList<>());

    @Autowired
    public TableCleaner tableCleaner;

    RestTemplate restTemplate = new RestTemplate();

    public VacancyParser() {
    }

    public void startParsing(String[] url, String foundUrl) throws SQLException, InterruptedException {
        System.out.printf("countpages: %s\n", countpages);
        tableCleaner.truncate();
        int counter = optionalCounter;
        int foundItems = foundItems(foundUrl);
        if (counter > 2000) counter = 2000;
        if(foundItems < counter) counter = foundItems;
        leftover = 0;
        int count = perPage;
        System.out.printf("counter: %s\n", counter);
        if (counter <= perPage) {
            countpages = 1;
            count = counter;}
        if (counter > perPage) {
            if ((counter%perPage) !=0) {
                leftover = counter % perPage;
                countpages = (counter - leftover) / perPage;
                if (leftover > 0) countpages = countpages+1;
            } else {
                countpages = counter/perPage;}
        }
        System.out.println("Страниц:" + countpages);//количество страниц
        System.out.println("Количество на страницу:" + count);//количество на страницу
        System.out.printf("%s*%s", url[0], url[1]);
        Parser.counterIDs = 1;
        int pagesCount = 0;
        countProtector = 1;
        System.out.println();
        List<Thread> listofThreads = new ArrayList<>();
        for (int j = 0; j < countpages; j++) {
            if (foundItems == 0) break;
            Models response = restTemplate.getForObject(url[0] + j + url[1], Models.class);
            Thread tp = new Thread(new Parser(response, j, count), "Parser-"+(j+1));
                listofThreads.add(tp);
                tp.start();
            pagesCount = j+1;
        }
        for (Thread x : listofThreads) {
            x.join();
        }
        System.out.println("Search Complete");
        System.out.println("Насчитал: " + Parser.integercountVacancy);
        System.out.println("counterIDs: " + Parser.counterIDs + "|| countProtector:" + countProtector);
        System.out.println("Cicles: "+ (pagesCount) + " || Items: " + unionvaclist.size());//+ ThreadParser.getListOfVacancies().size());
        //========возврат потоков на дефолтные значения - ThreadParser
        Parser.counterIDs = 1;
        Parser.integercountVacancy = 0;
        //===============================================
        countpages = 1; //возвращаем значение общей переменной
        countProtector = 1;
        leftover = 0;
    }

    public int foundItems(String url){
        Models responseFound = restTemplate.getForObject(url, Models.class);
        System.out.println("Found: " + responseFound.getFound());
        return responseFound.getFound();
    }


    public List<Vacancy> doParseWithAreas(String name, String area) throws SQLException, InterruptedException {
        String foundUrl = mainurl + "?per_page=" + 1 + "&page=" + 0 + "&text=" + name + "&area=" + area;
        String[] url = new String[]{mainurl + "?per_page=" + perPage + "&page=", "&text=" + name + "&area=" + area};
        startParsing(url, foundUrl);
        return unionvaclist;
    }

    public List<Vacancy> doParseOnlyArea(String area) throws InterruptedException, SQLException {
        String foundUrl = mainurl + "?per_page=" + 1 + "&page=" + 0 + "&area=" + area;
        String[] url = new String[]{mainurl + "?per_page=" + perPage + "&page=", "&area=" + area};
        startParsing(url, foundUrl);
        return unionvaclist;
       }

    public List<Vacancy> doParseOnlyName(String name) throws InterruptedException, SQLException {
        String foundUrl = mainurl + "?per_page=" + 1 + "&page=" + 0 + "&text=" + name;
        String[] url = new String[]{mainurl + "?per_page=" + perPage + "&page=", "&text=" + name};
        startParsing(url, foundUrl);
        return unionvaclist;
    }
}

