package com.example.gescov.DomainLayer.Classmodels;

import com.example.gescov.DomainLayer.Services.ISchoolService;
import com.example.gescov.DomainLayer.Services.ServicesFactory;

public class School {

    private String id;
    private String name;
    private String address;
    private String state;
    private String creator;
    private float latitude;
    private float longitude;

    public School(String id, String name, String address, String state, String creator) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.state = state;
        this.creator = creator;
        longitude = latitude = 0;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void createClassroom(String classroomName, String classrooomCapacity, String classroomRows, String classroomCols) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        schoolService.createClassroomRequest(name, address, state, latitude, longitude, creator, classroomName, classrooomCapacity, classroomRows, classroomCols);
    }
}
