//package ru.one.hhadvisor.program.threads;
//
//import ru.one.hhadvisor.output.Vacancy;
//import ru.one.hhadvisor.program.exp.ModelForExperience;
//import ru.one.hhadvisor.services.VacancyParser;
//
//public class ThreadSubParser {
//
//    VacancyParser.countProtector++;
//           if (VacancyParser.response.getItems().get(i).getSalary().getFrom() == null && VacancyParser.response.getItems().get(i).getSalary().getTo() == null) continue;
//            if (!VacancyParser.response.getItems().get(i).getSalary().getCurrency().equals("RUR")) continue;
//    integercountVacancy++;
//    Vacancy localvac = new Vacancy(null, ThreadParser.counterIDs,
//            VacancyParser.response.getItems().get(i).getName(),
//            VacancyParser.response.getItems().get(i).getEmployer().getName(),
//            VacancyParser.response.getItems().get(i).getArea().getName(),
//            VacancyParser.response.getItems().get(i).getSalary().getFrom(),
//            VacancyParser.response.getItems().get(i).getSalary().getTo(),
//            VacancyParser.response.getItems().get(i).getSalary().getCurrency(),
//            null,
//            null,
//            Integer.parseInt(VacancyParser.response.getItems().get(i).getArea().getId()),
//            Integer.parseInt(VacancyParser.response.getItems().get(i).getId())
//    );
//
//
//    String queryUrl = expurl+VacancyParser.response.getItems().get(i).getId();
//    ModelForExperience responseExp = restTemplate.getForObject(queryUrl, ModelForExperience.class);
//            assert responseExp != null;
//            localvac.setExperienceId(responseExp.getExperience().getId());
//            localvac.setExperienceName(responseExp.getExperience().getName());
//    //======== конвертер валюты
//            if (localvac.getSalaryCurrency().equals("USD")){
//        localvac.setSalaryCurrency("RUR");
//        localvac.setSalaryFrom(localvac.getSalaryFrom()*USD);
//        localvac.setSalaryTo(localvac.getSalaryTo()*USD);
//    }
//            if (localvac.getSalaryCurrency().equals("EUR")){
//        localvac.setSalaryCurrency("RUR");
//        localvac.setSalaryFrom(localvac.getSalaryFrom()*EUR);
//        localvac.setSalaryTo(localvac.getSalaryTo()*EUR);
//    }
//
//
//            VacancyParser.unionvaclist.add(localvac);
//    ThreadParser.counterIDs++;
//            if (ThreadParser.counterIDs > 2000) break;
//            if (VacancyParser.countProtector > 2000) break;
//
//
//
//}