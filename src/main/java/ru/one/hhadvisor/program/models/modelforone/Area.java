package ru.one.hhadvisor.program.models.modelforone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Area {
    private String id;
    private String name;


    public Area(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Area() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
