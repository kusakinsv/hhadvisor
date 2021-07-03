package ru.one.hhadvisor.program.statistics;

import org.springframework.beans.factory.support.ManagedList;
import ru.one.hhadvisor.output.Vacancy;
import ru.one.hhadvisor.program.exp.Experience;
import ru.one.hhadvisor.services.VacancyParser;

import java.util.ArrayList;
import java.util.List;

public class MinMaxStat {
    private int salaryFrom;
    private int salaryTo;
    int countermin = 0;
    int countermax = 0;
    private int column1l;
    private int column1Count;
    private int column1r;
    String column1s;
    private int column2l;
    private int column2Count;
    private int column2r;
    String column2s;
    private int column3l;
    private int column3Count;
    private int column3r;
    String column3s;
    private int column4l;
    private int column4Count;
    private int column4r;
    String column4s;
    private int column5l;
    private int column5Count;
    private int column5r;
    String column5s;
    private int column6l;
    private int column6Count;
    private int column6r;
    String column6s;
    private int mediana;


    public void doStat(List<Vacancy> vacancyList){
        int salarymin = Integer.MAX_VALUE;
        int salarymax = Integer.MIN_VALUE;;
        for (Vacancy v :vacancyList) {
            if (v.getSalaryFrom() != null) {
                if (v.getSalaryFrom() < salarymin) salarymin = v.getSalaryFrom();//roundThousand(v.getSalaryFrom()); //v.getSalaryFrom();
                countermin++;
            }
            if (v.getSalaryTo() != null) {
                if (v.getSalaryTo() > salarymax) salarymax =  v.getSalaryTo();//roundThousand(v.getSalaryTo()); //v.getSalaryTo();
                countermax++;
            }
//            if (salarymin > salarymax) {
//                int x = salarymax;
//                salarymax = salarymin;
//                salarymin = x;
//                countermax++;
//                countermin--;
//            }
                                }
        salarymin = roundThousand(salarymin);
        salarymax = roundThousand(salarymax);
        int totalInterval = salarymax - salarymin;
     System.out.println(salarymin +  " || " + salarymax );
        int serialInterval = totalInterval/6;
        column1r = salarymin+serialInterval;
        column2l = column1r;
        column2r = column2l+serialInterval;
        column3l = column2r;
        column3r = column3l+serialInterval;
        column4l = column3r;
        column4r = column4l+serialInterval;
        column5l = column4r;
        column5r = column5l+serialInterval;
        column6l = column5r;
        column6r = 0;//column6l+serialInterval;
        List<Integer> middleSalaruList = new ArrayList<>();
        List<Integer> forMedianCount = new ArrayList<>();
        for (Vacancy v :vacancyList) {
            if (v.getSalaryFrom() != null && v.getSalaryTo() != null) {
                int middleSalary = (v.getSalaryFrom() + v.getSalaryTo()) / 2;
                middleSalaruList.add(roundThousand(middleSalary));

            }
            if (v.getSalaryFrom() == null && v.getSalaryTo() != null) {
                int middleSalary = v.getSalaryTo();
                middleSalaruList.add(roundThousand(middleSalary));
            }
            if (v.getSalaryFrom() != null && v.getSalaryTo() == null) {
                int middleSalary = v.getSalaryFrom();
                middleSalaruList.add(roundThousand(middleSalary));

            }
        }
        for (Integer i :middleSalaruList) {
                if (i  < column1r) column1Count++;
                if (i  > column2l && i < column2r ) column2Count++;
                if (i  > column3l && i < column3r ) column3Count++;
                if (i  > column4l && i < column4r ) column4Count++;
                if (i  > column5l && i < column5r ) column5Count++;
                if (i  > column6l) column6Count++;
                         }

        List<Integer> medianlist = new ArrayList<>();
        for (int i = 0; i < column1Count ; i++) {
            medianlist.add((column1l+column1r)/2);
        }
        for (int i = 0; i < column2Count ; i++) {
            medianlist.add((column2l+column2r)/2);
        }
        for (int i = 0; i < column3Count ; i++) {
            medianlist.add((column3l+column3r)/2);
        }
        for (int i = 0; i < column4Count ; i++) {
            medianlist.add((column4l+column4r)/2);
        }
        for (int i = 0; i < column5Count ; i++) {
            medianlist.add((column5l+column5r)/2);
        }
        for (int i = 0; i < column6Count ; i++) {
            medianlist.add((column6l+column6r)/2);
        }

        if (medianlist.size()%2 == 0){
            int middle = medianlist.size()/2;
          mediana = (medianlist.get(middle)+medianlist.get(middle+1))/2;
        } else {
            int middle = (medianlist.size()-1)/2+1;
            mediana = medianlist.get(middle);
        }



        salaryFrom = salarymin;
        salaryTo = salarymax;
        column1s = "до " + column1r + " т.р.";
        column2s = "от " + column2l + " до " + column2r + " т.р.";
        column3s = "от " + column3l + " до " + column3r + " т.р.";
        column4s = "от " + column4l + " до " + column4r + " т.р.";
        column5s = "от " + column5l + " до " + column5r + " т.р.";
        column6s = "свыше " + column6l + " т.р.";
        System.out.println("STAT COMPLETE");


   }

    public MinMaxStat() {
    }

    public int getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(int salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public int getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(int salaryTo) {
        this.salaryTo = salaryTo;
    }

    public int getCountermin() {
        return countermin;
    }

    public void setCountermin(int countermin) {
        this.countermin = countermin;
    }

    public int getCountermax() {
        return countermax;
    }

    public void setCountermax(int countermax) {
        this.countermax = countermax;
    }

    public int getColumn1l() {
        return column1l;
    }

    public void setColumn1l(int column1l) {
        this.column1l = column1l;
    }

    public int getColumn1Count() {
        return column1Count;
    }

    public void setColumn1Count(int column1Count) {
        this.column1Count = column1Count;
    }

    public int getColumn1r() {
        return column1r;
    }

    public void setColumn1r(int column1r) {
        this.column1r = column1r;
    }

    public String getColumn1s() {
        return column1s;
    }

    public void setColumn1s(String column1s) {
        this.column1s = column1s;
    }

    public int getColumn2l() {
        return column2l;
    }

    public void setColumn2l(int column2l) {
        this.column2l = column2l;
    }

    public int getColumn2Count() {
        return column2Count;
    }

    public void setColumn2Count(int column2Count) {
        this.column2Count = column2Count;
    }

    public int getColumn2r() {
        return column2r;
    }

    public void setColumn2r(int column2r) {
        this.column2r = column2r;
    }

    public String getColumn2s() {
        return column2s;
    }

    public void setColumn2s(String column2s) {
        this.column2s = column2s;
    }

    public int getColumn3l() {
        return column3l;
    }

    public void setColumn3l(int column3l) {
        this.column3l = column3l;
    }

    public int getColumn3Count() {
        return column3Count;
    }

    public void setColumn3Count(int column3Count) {
        this.column3Count = column3Count;
    }

    public int getColumn3r() {
        return column3r;
    }

    public void setColumn3r(int column3r) {
        this.column3r = column3r;
    }

    public String getColumn3s() {
        return column3s;
    }

    public void setColumn3s(String column3s) {
        this.column3s = column3s;
    }

    public int getColumn4l() {
        return column4l;
    }

    public void setColumn4l(int column4l) {
        this.column4l = column4l;
    }

    public int getColumn4Count() {
        return column4Count;
    }

    public void setColumn4Count(int column4Count) {
        this.column4Count = column4Count;
    }

    public int getColumn4r() {
        return column4r;
    }

    public void setColumn4r(int column4r) {
        this.column4r = column4r;
    }

    public String getColumn4s() {
        return column4s;
    }

    public void setColumn4s(String column4s) {
        this.column4s = column4s;
    }

    public int getColumn5l() {
        return column5l;
    }

    public void setColumn5l(int column5l) {
        this.column5l = column5l;
    }

    public int getColumn5Count() {
        return column5Count;
    }

    public void setColumn5Count(int column5Count) {
        this.column5Count = column5Count;
    }

    public int getColumn5r() {
        return column5r;
    }

    public void setColumn5r(int column5r) {
        this.column5r = column5r;
    }

    public String getColumn5s() {
        return column5s;
    }

    public void setColumn5s(String column5s) {
        this.column5s = column5s;
    }

    public int getColumn6l() {
        return column6l;
    }

    public void setColumn6l(int column6l) {
        this.column6l = column6l;
    }

    public int getColumn6Count() {
        return column6Count;
    }

    public void setColumn6Count(int column6Count) {
        this.column6Count = column6Count;
    }

    public int getColumn6r() {
        return column6r;
    }

    public void setColumn6r(int column6r) {
        this.column6r = column6r;
    }

    public String getColumn6s() {
        return column6s;
    }

    public void setColumn6s(String column6s) {
        this.column6s = column6s;
    }

    public int getMediana() {
        return mediana;
    }

    public void setMediana(int mediana) {
        this.mediana = mediana;
    }
    public int roundThousand(int a){
        double d = Double.parseDouble(""+a);
        double z = (int) a;
        z = Math.round(z*0.001);
        a = (int) z;
        //System.out.println(a);
        return a;

    }
}
