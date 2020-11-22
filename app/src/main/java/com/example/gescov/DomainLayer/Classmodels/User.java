package com.example.gescov.DomainLayer.Classmodels;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.toolbox.JsonArrayRequest;
import com.example.gescov.DomainLayer.DomainControlFactory;
import com.example.gescov.DomainLayer.Services.IContagionService;
import com.example.gescov.DomainLayer.Services.ISchoolService;
import com.example.gescov.DomainLayer.Services.ServicesFactory;
import com.example.gescov.ViewLayer.SchoolClassroomList.SchoolRequestResult;
import com.example.gescov.ViewLayer.home.ContagionRequestResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private List<String> schoolsID;
    private String id;
    private String idContagion;
    private String ConfirmedInfected;
    private Boolean risk;
    private String profileType;

    public String getProfileType() {
        return profileType;
    }


    public void setProfileType(String profileType) {
        this.profileType = profileType;
        System.out.println("ha ido bien! :), nuevo perfil = " + profileType);
    }




    public  User() {
        schoolsID = new ArrayList<>();
    }

    public User(String Name, String userID) {
        this.name = Name;
        this.id = userID;
    }


    public User (String name, List<String> schools, String id, boolean risk, String profileType) {
        this.name = name;
        this.schoolsID =  schools;
        this.id = id;
        this.risk = risk;
        this.profileType = profileType;
    }



    public User(String name) { this.name = name;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSchoolsID() {
        return schoolsID;
    }

    public void setSchoolsID(List<String> schoolsID) {
        this.schoolsID = schoolsID;
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

    public void setRisk (boolean risk) {
        this.risk = risk;
    }

    public String getCntagionsOfCenter() {
        // ahora es una lista de schools
        String schoolId = schoolsID.get(0);
        IContagionService icontragionService = ServicesFactory.getContagionService();
        return icontragionService.getContagionList(name,schoolId);
    }

    public String getClassroomDimensions(String schoolId, String classroomId) {
        School school = DomainControlFactory.getSchoolsModelCrontroller().getSchoolById(schoolId);
        return school.getClassroomDimensions(schoolId,classroomId);
    }

    public String getStudentsInClassroom(String classroom) {
        School school = DomainControlFactory.getSchoolsModelCrontroller().getSchoolById(schoolsID.get(0));
        return school.getStudentsInClassroom(classroom);
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

    public void refreshUserParams(JSONObject response) {
        try {
            name = response.getString("name");
            profileType = response.getString("profile");
            risk = response.getBoolean("risk");
            JSONArray aux = response.getJSONArray("schoolsID");
            schoolsID = new ArrayList<>();
            for (int i = 0; i < aux.length(); ++i) {
                schoolsID.add((String) aux.get(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void addStudentToCenter(School school, MutableLiveData<SchoolRequestResult> result) {
        schoolsID.add(0,school.getId());
        ServicesFactory.getSchoolService().addStudentToCenter(id,school.getId(),result);
    }

    public void deleteSchoolClassroom(String classroomId) {
        ServicesFactory.getDeleteSchoolClassroomResponseController().deleteSchoolClassroomRequest(classroomId, id);
    }

    public void changeUSerProfile(String profile) {
        ServicesFactory.getUserService().changeUserProfile(id,profile);
    }

    public boolean getRisk() {
        return risk;
    }

    public void updateRisk() {
        ServicesFactory.getUpdateUserRiskResponseController().updateRisk(id);
    }
}
