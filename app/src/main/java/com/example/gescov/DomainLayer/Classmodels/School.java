package com.example.gescov.DomainLayer.Classmodels;

public class School {

    private String id;
    private  String name;
    private  String address;
    private  String state;
    private  String creator;

    public School() {
        id = "FIB";
    }

    public School(String name) {
        this.name = name;
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
