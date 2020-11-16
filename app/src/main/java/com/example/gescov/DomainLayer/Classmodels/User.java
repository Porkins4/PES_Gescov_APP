package com.example.gescov.DomainLayer.Classmodels;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.Services.IContagionService;
import com.example.gescov.DomainLayer.Services.ISchoolService;
import com.example.gescov.DomainLayer.Services.ServicesFactory;
import com.example.gescov.ViewLayer.home.ContagionRequestResult;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private School schools;
    private String id;
    private String idContagion;
    private String ConfirmedInfected;


    public  User() {
        //name = "El Bixo";
        schools = new School("FIB");
        schools.setId("5fa9d285e59d4c4c5d571519");
    }

    public User(String name) { this.name = name;}

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

    public String getIdContagion() { return idContagion; }

    public void setIdContagion(String idContagion) { this.idContagion = idContagion; }

    public String getConfirmedInfected() { return ConfirmedInfected; }

    public void setConfirmedInfected(String confirmedInfected) { ConfirmedInfected = confirmedInfected; }

    public String getCntagionsOfCenter() {
        // ahora es una lista de schools
        String schoolId = schools.getId();
        IContagionService icontragionService = ServicesFactory.getContagionService();
        return icontragionService.getContagionList(name,schoolId);
    }

    public String getClassroomDimensions(String schoolId, String classroomId) {
        return schools.getClassroomDimensions(schoolId,classroomId);
    }

    public String getStudentsInClassroom(String classroom) {
        return schools.getStudentsInClassroom(classroom);
    }

    public String getAllSchools() {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        return schoolService.getAllSchools();
    }

    public void refreshSchoolList() {
        ServicesFactory.getRefreshSchoolResponseController().refreshSchoolList(this.id);
    }

    public void notifiyContagion(MutableLiveData<ContagionRequestResult> result) {
        ConfirmedInfected = "true";
        IContagionService contagionService = ServicesFactory.getContagionService();
        contagionService.notifyContagion(result,ConfirmedInfected,id);
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
        ServicesFactory.getDeleteSchoolResponseController().deleteSchoolRequest(schoolId, this.id);

        //ISchoolService schoolService = retrofit.create(ISchoolService.class);
    }

    public String getSchoolClassrooms(String schoolName) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        return schoolService.getSchoolClassrooms(schoolName, this.name);
    }

    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result) {
        IContagionService contagionService = ServicesFactory.getContagionService();
        contagionService.notifyRecovery(result,id);
    }

    public void sendAnswers(List<Boolean> answers) {
        if ( idContagion != null) {
            IContagionService iContagionService = ServicesFactory.getContagionService();
            iContagionService.sendAnswers(answers, idContagion);
        }
    }
}
