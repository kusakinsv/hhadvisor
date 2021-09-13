package ru.one.hhadvisor.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import ru.one.hhadvisor.entity.repos.VacancyRepo;
import ru.one.hhadvisor.output.Vacancy;
import ru.one.hhadvisor.program.models.exp.ModelForExperience;
import ru.one.hhadvisor.program.models.model.Models;

import java.util.ArrayList;
import java.util.List;

public class Parser implements Runnable{
    public static int integercountVacancy = 0;
    public static int counterIDs = 1; //default = 1
    private int USD = 72;
    private int EUR = 86;
    final String expurl = "https://api.hh.ru/vacancies/";
    public List<Vacancy> localVacancyList = new ArrayList<>();
    private Models response;
    private int page;
    private int count;


    @Autowired
    private VacancyRepo vacancyRepo;

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
        System.out.println("Поток " + getClass().getSimpleName()+"-"+page + " запущен");
        if (VacancyParser.round == VacancyParser.countpages) count = VacancyParser.leftover; // ??
        System.out.println("Вакансий " + count);
        for (int i = 0; i < count; i++) {

            VacancyParser.countProtector++;
            if (response.getItems().get(i).getSalary().getFrom() == null && response.getItems().get(i).getSalary().getTo() == null)
            {
                continue;
            } else {
                integercountVacancy++;

                if (!response.getItems().get(i).getSalary().getCurrency().equals("RUR")) continue;

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
                assert responseExp != null;
                localvac.setExperienceId(responseExp.getExperience().getId());
                localvac.setExperienceName(responseExp.getExperience().getName());
                //======== конвертер валюты
                if (localvac.getSalaryCurrency().equals("USD") && localvac.getSalaryCurrency() != null ) {
                    localvac.setSalaryCurrency("RUR");
                    localvac.setSalaryFrom(localvac.getSalaryFrom() * USD);
                    localvac.setSalaryTo(localvac.getSalaryTo() * USD);
                }
                if (localvac.getSalaryCurrency().equals("EUR") && localvac.getSalaryCurrency() != null) {
                    localvac.setSalaryCurrency("RUR");
                    localvac.setSalaryFrom(localvac.getSalaryFrom() * EUR);
                    localvac.setSalaryTo(localvac.getSalaryTo() * EUR);
                }


                VacancyParser.unionvaclist.add(localvac);
//               vacancyRepo.save(localvac);

                Parser.counterIDs++;
                if (Parser.counterIDs > 2000) break;
                if (VacancyParser.countProtector > 2000) break;
            }
        }
        System.out.println("Поток " + getClass().getSimpleName()+"-"+page + " завершен");
    }
}
