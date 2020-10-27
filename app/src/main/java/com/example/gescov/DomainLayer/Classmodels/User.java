package com.example.gescov.DomainLayer.Classmodels;

import com.example.gescov.DomainLayer.Services.IContagionService;
import com.example.gescov.DomainLayer.Services.ServicesFactory;

public class User {

    private String name;
    private School schools;
    private String id;

    public  User(){
        name = "El Bixo";
        schools = new School();
    }
    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchools() {
        return schools;
    }

    public void setSchools(School schools) {
        this.schools = schools;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCntagionsOfCenter() {
        String schoolId = schools.getId();
        IContagionService icontragionService = ServicesFactory.getContagionService();
        return icontragionService.getContagionList(name,schoolId);
    }

    public String getClassroomDimensions(String schoolId, String classroomId) {
        return schools.getClassroomDimensions(schoolId,classroomId);
    }
}
