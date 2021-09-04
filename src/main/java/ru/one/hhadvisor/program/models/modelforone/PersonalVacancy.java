package ru.one.hhadvisor.program.models.modelforone;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalVacancy {
    @JsonProperty("id")
    private  Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("area")
    private Area area;
    @JsonProperty("experience")
    private Experience experience;
    @JsonProperty("employer")
    private Employer employer;


}
