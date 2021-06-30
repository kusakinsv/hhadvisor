package ru.one.hhadvisor.program;

import ru.one.hhadvisor.program.exp.Experience;

public class NullableExperience extends Experience {
    private String id = null;
    private String name = null;

    public NullableExperience() {
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

    public NullableExperience(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

