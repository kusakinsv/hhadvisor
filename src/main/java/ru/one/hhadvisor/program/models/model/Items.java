package ru.one.hhadvisor.program.models.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.one.hhadvisor.program.models.NullableSalary;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    private Employer employer;
    private Area area;
    private Salary salary;

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Salary getSalary() {
        if (salary == null) return new NullableSalary();
        else return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Items(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

