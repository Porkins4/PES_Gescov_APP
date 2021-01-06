package com.example.gescov.domainlayer.Classmodels;

import org.json.JSONException;
import org.json.JSONObject;

public class Classroom {
    private String id;
    private String name;
    private int rows;
    private int columns;
    private int capacity;
    private String schoolID;

    public Classroom() {}

    public Classroom(String id, String name, int rows, int columns, String schoolID) {
        this.id = id;
        this.name = name;
        this.rows = rows;
        this.columns = columns;
        this.capacity = rows*columns;
        this.schoolID = schoolID;
    }

    public static Classroom fromJSONtoClassroom(JSONObject jsonObject) {
        Classroom result = new Classroom();
        try {
            String id = jsonObject.getString("id");
            String name = jsonObject.getString("name");
            int numRows = jsonObject.getInt("numRows");
            int numCols = jsonObject.getInt("numCols");
            String schoolID = jsonObject.getString("schoolID");
            return new Classroom(id,name,numRows,numCols,schoolID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
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


    public void print() {
        System.out.println(id);
        System.out.println(name);
        System.out.println(rows);
        System.out.println(columns);
        System.out.println(capacity);
    }
}