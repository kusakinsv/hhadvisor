package ru.one.hhadvisor.program.models;

import ru.one.hhadvisor.program.models.model.Salary;

public class NullableSalary extends Salary {
    private Integer from = null;
    private Integer to = null;
    private String currency = null;

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}