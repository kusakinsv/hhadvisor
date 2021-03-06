package ru.one.hhadvisor.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vacancy {
    private  Integer id;
    private  String name;
    private  String company;
    private  String area;
    private  Integer salaryFrom;
    private  Integer salaryTo;
    private  String salaryCurrency;
    private  String experienceId;
    private  String experienceName;
    private  Integer getAreaid;
    private  Integer uniqueId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dbid;

    public Vacancy() {
    }

    public Vacancy(Integer dbid, Integer id, String name, String company, String area, Integer salaryFrom, Integer salaryTo, String salaryCcurrency, String experienceId, String experienceName, Integer getAreaid, Integer uniqueId) {
        this.dbid = dbid;
        this.id = id;
        this.name = name;
        this.company = company;
        this.area = area;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.salaryCurrency = salaryCcurrency;
        this.experienceId = experienceId;
        this.experienceName = experienceName;
        this.getAreaid = getAreaid;
        this.uniqueId = uniqueId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(Integer salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public Integer getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(Integer salaryTo) {
        this.salaryTo = salaryTo;
    }

    public String getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(String salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    public Integer getGetAreaid() {
        return getAreaid;
    }

    public void setGetAreaid(Integer getAreaid) {
        this.getAreaid = getAreaid;
    }

    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(String experienceId) {
        this.experienceId = experienceId;
    }

    public String getExperienceName() {
        return experienceName;
    }

    public void setExperienceName(String experienceName) {
        this.experienceName = experienceName;
    }
}
