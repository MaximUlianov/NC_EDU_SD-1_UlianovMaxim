package com.netcracker.edu.fapi.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {

    private long id;
    private String name;
    private String description;
    private double proceeds;

    public Company() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getProceeds() {
        return proceeds;
    }

    public void setProceeds(double proceeds) {
        this.proceeds = proceeds;
    }
}
