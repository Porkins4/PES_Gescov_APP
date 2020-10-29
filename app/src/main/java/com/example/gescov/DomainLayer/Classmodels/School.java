package com.example.gescov.DomainLayer.Classmodels;

import com.example.gescov.DomainLayer.Services.ISchoolService;
import com.example.gescov.DomainLayer.Services.ServicesFactory;

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

    public String getClassroomDimensions(String schoolId, String classroomId) {
        ISchoolService iSchoolService = ServicesFactory.getSchoolService();
        return iSchoolService.getDimensions(schoolId,classroomId);
    }

    public String getStudentsInClassroom(String classroom) {
        ISchoolService iSchoolService = ServicesFactory.getSchoolService();
        return iSchoolService.getStudentsInClassroom(classroom);
    }
}
