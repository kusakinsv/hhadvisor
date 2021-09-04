package ru.one.hhadvisor.program.models;

import ru.one.hhadvisor.program.models.model.Salary;

public class NullableSalary extends Salary {
    private Integer from = null;
    private Integer to = null;
    private String currency = null;

//    public NullableSalary() {
//        this.from = 0;
//        this.to = 0;
//        this.currency = "null";    }

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

//    public NullableSalary(Integer from, Integer to, String currency) {
//        this.from = 0;
//        this.to = 0;
//        this.currency = "null";
//    }
//    public static Object nullableObj(Object z) {
//        if (z == null) return new NullableSalary();
//        else return z;	}

}