package com.example.gescov.DomainLayer.Classmodels;

import com.example.gescov.DomainLayer.Services.IContagionService;
import com.example.gescov.DomainLayer.Services.ISchoolService;
import com.example.gescov.DomainLayer.Services.ServicesFactory;

public class User {

    private String name;
    private School school;
    private String id;

    public  User(){
        name = "El Bixo";
        school = new School("FIB", "FIB", "adress", "state", "creator");
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
        IContagionService contagionService = ServicesFactory.getContagionService();
        return contagionService.notifyContagion();

    }

    public void sendReservationRequest(String aula, int row, int col) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        schoolService.sendReservationRequest(name,aula,row,col);
    }

    public void createSchool(String schoolName, String schoolAddress) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        schoolService.createSchoolRequest(schoolName,schoolAddress,name);
    }

    public void notifyRecovery() {
        IContagionService contagionService = ServicesFactory.getContagionService();
        contagionService.notifyRecovery();
    }
}
