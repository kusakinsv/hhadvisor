package ru.one.hhadvisor.takeinfo;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.client.RestTemplate;
import ru.one.hhadvisor.program.SearchParametersVacancy;
import ru.one.hhadvisor.program.Vacancies;
import ru.one.hhadvisor.program.VacancyOld;

import java.util.Objects;

// ПОЛУЧАЕТ И ПЕРЕДАЕТ ДАННЫЕ

public class VacancyRecipient {
    private RestTemplate restTemplate = new RestTemplate();
    private static String adress = "https://api.hh.ru/vacancies";

    public Vacancies getAllVacancies() {
        return restTemplate.getForEntity(adress, Vacancies.class).getBody();
    }

    public Vacancies getAllVacancies(SearchParametersVacancy params) {//не работают запросы по параметрам
       // String url = adress + "/?per_page20";
        String url = adress + "?text=Java";
        if (Strings.isNotBlank(params.getName()))  url += "&text=" + params.getName() + "&search_field=name";
        if (Strings.isNotBlank(params.getSpecializationId()))  url += "&specialization=" + params.getSpecializationId();
        if (Strings.isNotBlank(params.getExperienceId()))  url += "&experience=" + params.getExperienceId();
        if (Strings.isNotBlank(params.getAreaId())) url += "&area=" + params.getAreaId();
        if (Strings.isNotBlank(params.getScheduleId()))  url += "&schedule=" + params.getScheduleId();
        if (Objects.nonNull(params.getSalary()))  url += "&salary=" + params.getSalary();
        if (Objects.nonNull(params.getPage()))  url += "&page=" + params.getPage();
        return restTemplate.getForEntity(url, Vacancies.class).getBody();
    }

//    public Vacancy getVacancyById(String id) {//работает запрос по id
//        System.out.println(adress + "/" + id);
//
//        return restTemplate.getForEntity(adress + "/" + id, Vacancy.class).getBody();

 //   }
    public VacancyOld getVacancyById(String id) {//работает запрос по id
        System.out.println(adress + id);

        return restTemplate.getForEntity(adress + id, VacancyOld.class).getBody();

    }
//    public Vacancy getVacancyByName(String name) {//работает запрос по id
//        System.out.println(adress + "/" + name);
//        return restTemplate.getForEntity(adress + "?text=" + name, Vacancy.class).getBody();
//    }
//    public Vacancy getVacancyByName(String name) {//работает запрос по id
//        return restTemplate.getForEntity(adress + "/" + name, Vacancy.class).getBody();
//    }

}

