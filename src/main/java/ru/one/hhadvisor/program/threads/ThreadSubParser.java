//package ru.one.hhadvisor.program.threads;
//
//import org.springframework.web.client.RestTemplate;
//import ru.one.hhadvisor.output.Vacancy;
//import ru.one.hhadvisor.program.exp.ModelForExperience;
//import ru.one.hhadvisor.services.VacancyParser;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ThreadSubParser extends Thread {
//
//    private String url = "https://api.hh.ru/vacancies/";
//    RestTemplate restTemplate = new RestTemplate();
//
//    @Override
//    public void run() {
//        VacancyParser.countProtector++;
//        if (VacancyParser.response.getItems().get(i).getSalary().getFrom() == null && VacancyParser.response.getItems().get(i).getSalary().getTo() == null) stop();
//        if (!VacancyParser.response.getItems().get(i).getSalary().getCurrency().equals("RUR")) stop();
//        //integercountVacancy++;
//        List<Vacancy> localVacList = new ArrayList<>();
//        Vacancy threadVac = new Vacancy(null, ThreadParser.counterIDs,
//                VacancyParser.response.getItems().get(i).getName(),
//                VacancyParser.response.getItems().get(i).getEmployer().getName(),
//                VacancyParser.response.getItems().get(i).getArea().getName(),
//                VacancyParser.response.getItems().get(i).getSalary().getFrom(),
//                VacancyParser.response.getItems().get(i).getSalary().getTo(),
//                VacancyParser.response.getItems().get(i).getSalary().getCurrency(),
//                null,
//                null,
//                Integer.parseInt(VacancyParser.response.getItems().get(i).getArea().getId()),
//                Integer.parseInt(VacancyParser.response.getItems().get(i).getId()));
//        String queryUrl = expurl+VacancyParser.response.getItems().get(i).getId();
//        ModelForExperience responseExp = restTemplate.getForObject(queryUrl, ModelForExperience.class);
//        assert responseExp != null;
//        threadVac.setExperienceId(responseExp.getExperience().getId());
//        threadVac.setExperienceName(responseExp.getExperience().getName());
//
//        ThreadParser.counterIDs++;
//        if (ThreadParser.counterIDs > 2000) break;
//        if (VacancyParser.countProtector > 2000) break;
//
//    }
//}
