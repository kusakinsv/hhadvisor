package ru.one.hhadvisor.program.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Models {
    private List<Items> items;
    private Integer found;
    private Integer pages;
    private Integer per_page;
    private Integer page;


    public Models() {}

    public Models(List<Items> items, Integer found, Integer pages, Integer per_page, Integer page) {
        this.items = items;
        this.found = found;
        this.pages = pages;
        this.per_page = per_page;
        this.page = page;
    }

    public Integer getFound() {
        return found;
    }

    public void setFound(Integer found) {
        this.found = found;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
