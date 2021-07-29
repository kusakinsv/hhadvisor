package ru.one.hhadvisor.services;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.one.hhadvisor.output.Vacancy;
import ru.one.hhadvisor.program.TableCleaner;
import ru.one.hhadvisor.program.model.Models;
import ru.one.hhadvisor.program.threads.ThreadParser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacancyParser {
    public final static int maincounter = 2000;
    public static int counter = maincounter;
    public static final String mainurl = "https://api.hh.ru/vacancies";
    public static int countpages = 1; //default = 1
    public static String name;
    public static String area;
    public static int icount;
    public static int countProtector = 1;
    public static int leftover = 0;
    public static int round = 0;
    public static Models response;
    public static List<Vacancy> unionvaclist = new ArrayList<>();
    public List<Vacancy> vacloc1 = new ArrayList<>();
    public VacancyParser(List<Vacancy> vacloc1) {
        this.vacloc1 = vacloc1;
     }
    public void doPutListVac(List<Vacancy> a){
        unionvaclist.addAll(a);
    }
    public void doUnionHere(Vacancy v, int i){
        vacloc1.add(v);
        if (i == VacancyParser.icount-1) doPutListVac(vacloc1);
    }
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    RestTemplate restTemplate = new RestTemplate();

    public VacancyParser() {
    }

    public List<Vacancy> doParseWithAreas(String name, String area) throws SQLException, InterruptedException {
        List<Vacancy> vacloc2 = new ArrayList<>();
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
        System.out.println("Количество на страницу:" + icount);//количество страниц
        System.out.println(url);
        ThreadParser.counterIDs = 1;
        int pagesCount = 0;
        countProtector = 1;
        System.out.println();
        List<ThreadParser> listofThreads = new ArrayList<>();
        for (int j = 0; j <= countpages; j++) {
            if (foundItems == 0) break;
            url = VacancyParser.mainurl + "?per_page=" + icount + "&page=" + VacancyParser.round + "&text=" + VacancyParser.name + "&area=" + VacancyParser.area;
            response = restTemplate.getForObject(url, Models.class);
            //  System.out.println("FFFFFF: " + VacancyParser.response.getItems().get(0).getName())
            round = j;
            ThreadParser tp = new ThreadParser();
            if (j < countpages){
            listofThreads.add(tp);
            tp.start();
            } else {
                listofThreads.add(tp);
                tp.start();
                for (ThreadParser x : listofThreads
                     ) {
                  x.join();
                }
            }
            pagesCount = j+1;
        }

//        while(){//=========Упразднено
//            Thread.sleep(100);
//            System.out.println("счетчик потоков = " + ThreadParser.threadCounter);
//            System.out.println("стр = " + countProtector);
//
//        }

        System.out.println("Search Complete");
        System.out.println("насчитал: " + ThreadParser.integercountVacancy);
        System.out.println("counterIDs: " + ThreadParser.counterIDs + "|| countProtector:" + countProtector);
        System.out.println("Cicles: "+ pagesCount + " || Items: " + unionvaclist.size());//+ ThreadParser.getListOfVacancies().size());
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
        VacancyParser.area = "";
        VacancyParser.name = "";
        return unionvaclist;
    }

    public List<Vacancy> doParseWithArea(String area) throws InterruptedException, SQLException {
        VacancyParser.area = area;
        TableCleaner tc = new TableCleaner();
        tc.truncate("vacancy");
        System.out.println("Count:" + counter + " || Area: " + area);
        if (counter > 2000) counter = 2000;
        String url = mainurl + "?per_page=" + 1 + "&page=" + 0 + "&area=" + area;
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
            if (foundItems == 0) break;
            url = VacancyParser.mainurl + "?per_page=" + 20 + "&page=" + VacancyParser.round + "&area=" + VacancyParser.area;
            response = restTemplate.getForObject(url, Models.class);
            round = j;
            ThreadParser tp = new ThreadParser();
            tp.run();
            pagesCount = j+1;
        }
//        while(ThreadParser.threadCounter !=countpages && foundItems != 0){//=========Упразднено
//            Thread.sleep(100);
//            System.out.println("счетчик потоков = " + ThreadParser.threadCounter);
//            System.out.println("стр = " + countpages);
//
//        }
        System.out.println("Search Complete");
        System.out.println("насчитал: " + ThreadParser.integercountVacancy);
        System.out.println("counterIDs: " + ThreadParser.counterIDs + "|| countProtector:" + countProtector);
        System.out.println("Cicles: "+ pagesCount + " || Items: "); //+ ThreadParser.getListOfVacancies().size());
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
        VacancyParser.area = "";
        VacancyParser.name = "";
        return unionvaclist;
       }

    public List<Vacancy> doParseWithName(String name) throws InterruptedException, SQLException {
        VacancyParser.name = name;
        TableCleaner tc = new TableCleaner();
        tc.truncate("vacancy");
        System.out.println("Text:" + name + " || Count:" + counter);
        if (counter > 2000) counter = 2000;
        String url = mainurl + "?per_page=" + 1 + "&page=" + 0 + "&text=" + name;
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
            if (foundItems == 0) break;
            url = VacancyParser.mainurl + "?per_page=" + 20 + "&page=" + VacancyParser.round + "&text=" + VacancyParser.name;
           // url = mainurl + "?per_page=" + 20 + "&page=" + j + "&text=" + name;
            response = restTemplate.getForObject(url, Models.class);
            round = j;
            ThreadParser tp = new ThreadParser();
            tp.run();
            pagesCount = j+1;

        }
//        while(ThreadParser.threadCounter !=countpages+1 && foundItems != 0){//=========Упразднено
//            Thread.sleep(100);
//            System.out.println("счетчик потоков = " + ThreadParser.threadCounter);
//            System.out.println("стр = " + countpages);
//        }

        System.out.println("Search Complete");
        System.out.println("насчитал: " + ThreadParser.integercountVacancy);
        System.out.println("counterIDs: " + ThreadParser.counterIDs + "|| countProtector:" + countProtector);
        System.out.println("Cicles: "+ pagesCount + " || Items:");// " + ThreadParser.getListOfVacancies().size());
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
        VacancyParser.area = "";
        VacancyParser.name = "";
        return unionvaclist;
    }
    public List<Vacancy> getVacloc1() {
        return vacloc1;
    }
    public void setVacloc1(List<Vacancy> vacloc1) {
        this.vacloc1 = vacloc1;
    }
}

