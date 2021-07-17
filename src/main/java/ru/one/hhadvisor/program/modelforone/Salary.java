package ru.one.hhadvisor.program.modelforone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.one.hhadvisor.program.NullableSalary;


    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Salary {
        private Integer from;
        private Integer to;
        private String currency;

        public Salary() {
        }
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

        public Salary(Integer from, Integer to, String currency) {
            this.from = from;
            this.to = to;
            this.currency = currency;
        }

        public static Object nullableObj(Object z) {
            if (z == null) return new NullableSalary();
            else return z;	}

    }

