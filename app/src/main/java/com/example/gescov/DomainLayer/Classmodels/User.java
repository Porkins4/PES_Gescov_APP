package com.example.gescov.DomainLayer.Classmodels;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.DomainControlFactory;
import com.example.gescov.DomainLayer.Services.IContagionService;
import com.example.gescov.DomainLayer.Services.IRefreshSchoolClassroomsService;
import com.example.gescov.DomainLayer.Services.ISchoolService;
import com.example.gescov.DomainLayer.Services.ServicesFactory;
import com.example.gescov.ViewLayer.SchoolClassroomList.SchoolRequestResult;
import com.example.gescov.ViewLayer.home.ContagionRequestResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private School schools;
    private String id;
    private String idContagion;
    private String ConfirmedInfected;
    private Boolean risk;
    private String profileType;


    public  User() {
        schools = new School();
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

    public void createSchool(String schoolName, String schoolAddress, String schoolTelephone, String schoolWebsite) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        List<String> administratorsList = new ArrayList<>();
        administratorsList.add(id);
        schoolService.createSchoolRequest(schoolName, schoolAddress, schoolTelephone, schoolWebsite, administratorsList, id);
    }

    public void deleteSchool(String schoolId) {
        ServicesFactory.getDeleteSchoolResponseController().deleteSchoolRequest(schoolId, this.id);

        //ISchoolService schoolService = retrofit.create(ISchoolService.class);
    }

    public void getSchoolClassrooms(String schoolName) {
        ServicesFactory.getRefreshSchoolClassroomsResponseController().refreshSchoolClassroomsList(schoolName);
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

    public void updateSchoolClassroom(String classroomId, String classroomName, int numRows, int numCols, int capacity) {
        ServicesFactory.getUpdateSchoolClassroomController().updateSchoolClassroom(classroomId, classroomName, numRows, numCols, capacity);
    }

    public void getTypeProfile() {
        ServicesFactory.getSchoolService().getTypeProfile(id);
    }

    public void setProfile(JSONObject response) {
        try {
            name = response.getString("name");
            profileType = response.getString("profile");
            risk = response.getBoolean("risk");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void addStudentToCenter(School school, MutableLiveData<SchoolRequestResult> result) {
        schools = school;
        ServicesFactory.getSchoolService().addStudentToCenter(id,school.getId(),result);
    }

    public void deleteSchoolClassroom(String classroomId) {
        ServicesFactory.getDeleteSchoolClassroomResponseController().deleteSchoolClassroomRequest(classroomId, id);
    }
}
