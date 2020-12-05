package com.example.gescov.DomainLayer.Classmodels;

public class Classroom {
    String id;
    String name;
    int rows;
    int columns;
    int capacity;

    public Classroom() {}

    public Classroom(String id, String name, int rows, int columns) {
        this.id = id;
        this.name = name;
        this.rows = rows;
        this.columns = columns;
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

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }



}