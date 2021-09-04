package ru.one.hhadvisor.output;

import java.util.List;

public class Output {
    private List<Vacancy> output;


    public Output() {
    }
    public List<Vacancy> getVacancyList() {
        return output;
    }

    public Output(List<Vacancy> vacancyList) {
        this.output = vacancyList;
    }

    public void setVacancyList(List<Vacancy> vacancyList) {
        this.output = vacancyList;


    }
}
