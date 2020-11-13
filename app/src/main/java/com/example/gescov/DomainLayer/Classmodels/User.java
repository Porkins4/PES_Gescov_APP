package com.example.gescov.DomainLayer.Classmodels;

import com.example.gescov.DomainLayer.Services.IContagionService;
import com.example.gescov.DomainLayer.Services.ISchoolService;
import com.example.gescov.DomainLayer.Services.ServicesFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class User {

    private String name;
    private School school;
    private String id;

    public  User(){
        name = "El Bixo";
        school = new School("FIB");
        id = "5fa9d10c8b2a355d3cd4127a";
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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCntagionsOfCenter() {
        String schoolId = school.getId();
        IContagionService icontragionService = ServicesFactory.getContagionService();
        return icontragionService.getContagionList(name,schoolId);
    }


    public String getClassroomDimensions(String schoolId, String classroomId) {
        return school.getClassroomDimensions(schoolId,classroomId);
    }

    public String getStudentsInClassroom(String classroom) {
        return school.getStudentsInClassroom(classroom);
    }

    public String getAllSchools() {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        return schoolService.getAllSchools();
    }

    public Boolean notifiyContagion() {
        IContagionService contagionServiceService = ServicesFactory.getContagionService();
        return contagionServiceService.notifyContagion();

    }

    public void sendReservationRequest(String aula, int row, int col) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        schoolService.sendReservationRequest(name,aula,row,col);
    }

    public void createSchool(String schoolName, String schoolAddress, String schoolState, String schoolWebsite) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        List<String> administratorsList = new ArrayList<>();
        administratorsList.add(id);
        schoolService.createSchoolRequest(schoolName, schoolAddress, schoolState, schoolWebsite, administratorsList, id);
    }

    public void deleteSchool(String schoolId) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        schoolService.deleteSchoolRequest(schoolId, this.id);

        //ISchoolService schoolService = retrofit.create(ISchoolService.class);

    }

    public String getSchoolClassrooms(String schoolName) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        return schoolService.getSchoolClassrooms(schoolName, this.name);
    }
}
