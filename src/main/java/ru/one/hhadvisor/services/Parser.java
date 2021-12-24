package ru.one.hhadvisor.services;

import lombok.SneakyThrows;
import org.springframework.web.client.RestTemplate;
import ru.one.hhadvisor.entity.Vacancy;
import ru.one.hhadvisor.program.models.exp.ModelForExperience;
import ru.one.hhadvisor.program.models.model.Models;

import java.util.ArrayList;
import java.util.List;

public class Parser implements Runnable{
    public static int integercountVacancy = 0;
    public static int countProtector = 0;
    public static int counterIDs = 1; //default = 1
    private int USD = 72;
    private int EUR = 86;
    final String expurl = "https://api.hh.ru/vacancies/";
    public List<Vacancy> localVacancyList = new ArrayList<>();
    private Models response;
    private int page;
    private int count;

    public Parser(Models response, int page, int count) {
        this.response = response;
        this.page = page;
        this.count = count;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(200);
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("Поток " + Thread.currentThread().getName() + " запущен");
        if (page == VacancyParser.countpages-1) count = VacancyParser.leftover; // ??
        for (int i = 0; i < count; i++) {
            Parser.countProtector++;
//            if (response.getItems().get(i).getSalary().getFrom() == null && response.getItems().get(i).getSalary().getTo() == null)
//            {
//                continue;
//            } else {
            integercountVacancy++;
//                if (!response.getItems().get(i).getSalary().getCurrency().equals("RUR")) continue;
            Vacancy localvac = new Vacancy(null, Parser.counterIDs,
                    response.getItems().get(i).getName(),
                    response.getItems().get(i).getEmployer().getName(),
                    response.getItems().get(i).getArea().getName(),
                    response.getItems().get(i).getSalary().getFrom(),
                    response.getItems().get(i).getSalary().getTo(),
                    response.getItems().get(i).getSalary().getCurrency(),
                    null,
                    null,
                    Integer.parseInt(response.getItems().get(i).getArea().getId()),
                    Integer.parseInt(response.getItems().get(i).getId())
            );
            String queryUrl = expurl + response.getItems().get(i).getId();
            ModelForExperience responseExp = restTemplate.getForObject(queryUrl, ModelForExperience.class);
//            assert responseExp != null;
//            localvac.setExperienceId(responseExp.getExperience().getId());
//            localvac.setExperienceName(responseExp.getExperience().getName());
//            //======== конвертер валюты
//            if (localvac.getSalaryCurrency().equals("USD") && localvac.getSalaryCurrency() != null) {
//                localvac.setSalaryCurrency("RUR");
//                if (localvac.getSalaryFrom()!=null) localvac.setSalaryFrom(localvac.getSalaryFrom() * USD);
//                if (localvac.getSalaryTo() !=null) localvac.setSalaryTo(localvac.getSalaryTo() * USD);
//            }
//            if (localvac.getSalaryCurrency().equals("EUR") && localvac.getSalaryCurrency() != null) {
//                localvac.setSalaryCurrency("RUR");
//                if (localvac.getSalaryFrom()!= null) localvac.setSalaryFrom(localvac.getSalaryFrom() * EUR);
//                if (localvac.getSalaryTo() != null) localvac.setSalaryTo(localvac.getSalaryTo() * EUR);
//            }
            VacancyParser.unionvaclist.add(localvac);
            Parser.counterIDs++;
            if (Parser.counterIDs > 2000) break;
            if (countProtector == 2000) break; // не позволяет искать более 2000 вакансий
        }
//    }
        System.out.println("Поток " + Thread.currentThread().getName() + " завершен");
    }
}
