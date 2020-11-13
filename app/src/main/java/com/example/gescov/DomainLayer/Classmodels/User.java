package com.example.gescov.DomainLayer.Classmodels;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.Services.IContagionService;
import com.example.gescov.DomainLayer.Services.ISchoolService;
import com.example.gescov.DomainLayer.Services.ServicesFactory;
import com.example.gescov.ViewLayer.home.ContagionRequestResult;

import java.util.List;

public class User {

    private String name;
    private School school;
    private String id;

    public  User(){
        name = "El Bixo";
        school = new School("5fa9d285e59d4c4c5d571519", "FIB", "adress", "state", "creator");
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


    public void notifiyContagion(MutableLiveData<ContagionRequestResult> result) {
        IContagionService contagionService = ServicesFactory.getContagionService();
        contagionService.notifyContagion(result);
    }


    public void sendReservationRequest(String aula, int row, int col) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        schoolService.sendReservationRequest(name,aula,row,col);
    }

    public void createSchool(String schoolName, String schoolAddress) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        schoolService.createSchoolRequest(schoolName,schoolAddress,name);
    }


    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result) {
        IContagionService contagionService = ServicesFactory.getContagionService();
        contagionService.notifyRecovery(result);
    }
    public void sendAnswers(List<Boolean> answers) {
        IContagionService iContagionService = ServicesFactory.getContagionService();
        iContagionService.sendAnswers(answers);

    }
}
