package com.example.gescov.domainlayer.Classmodels;

import java.util.List;

public class Subject {
    private String id;
    private String name;
    private List<String> teachers;
    private String schoolID;
    private List<String> students;

    public Subject(String id, String name,  String schoolID, List<String> students, List<String> teachers) {
        this.id = id;
        this.name = name;
        this.teachers = teachers;
        this.schoolID = schoolID;
        this.students = students;
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

    public List<String> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<String> teachers) {
        this.teachers = teachers;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }
}
