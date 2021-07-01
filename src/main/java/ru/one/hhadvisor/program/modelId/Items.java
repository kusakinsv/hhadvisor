package ru.one.hhadvisor.program.modelId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.one.hhadvisor.program.NullableSalary;
import ru.one.hhadvisor.program.model.Area;
import ru.one.hhadvisor.program.model.Employer;
import ru.one.hhadvisor.program.model.Salary;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
    @JsonProperty("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Items() {
    }

    public Items(String id) {
        this.id = id;
    }
}


