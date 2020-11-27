package com.example.gescov.DomainLayer.Classmodels;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.IContagionService;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.ISchoolService;
import com.example.gescov.DomainLayer.Singletons.ServicesFactory;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolRequestResult;
import com.example.gescov.viewlayer.home.ContagionRequestResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class User {

    public enum UserProfileType {
        STUDDENT ("Studdent"),
        TEACHER ("Teacher");

        private final String value;
        UserProfileType(String value) {
            this.value = value;
        }

        public static UserProfileType getUserProfileFromString(String value) {
            for (UserProfileType upf : values()) {
                if (upf.value.equals(value))
                    return upf;
            }
            return null;
        }

    }

    private String name;
    private List<String> schoolsID;
    private School schools;
    private String id;
    private String idContagion;
    private String ConfirmedInfected;
    private Boolean risk;
    private UserProfileType profileType;

    public UserProfileType getProfileType() {
        return profileType;
    }


    public void setProfileType(String profileType) {
        this.profileType = UserProfileType.getUserProfileFromString(profileType);
    }

    public  User() {
        schoolsID = new ArrayList<>();
    }

    public User(String Name, String userID) {
        this.name = Name;
        this.id = userID;
    }

    //----------------------------------
    public void setIdContagion(String idContagion) { this.idContagion = idContagion; }

    public User (String name, List<String> schools, String id, boolean risk, String profileType) {
        this.name = name;
        this.schoolsID =  schools;
        this.id = id;
        this.risk = risk;
        this.profileType = UserProfileType.getUserProfileFromString(profileType);
    }
    //----------------------------------


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
        IContagionService contagionService = ServicesFactory.getContagionService();
        contagionService.notifyContagion(result,"true",id);
    }


    public void sendReservationRequest(String aula, int row, int col) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        schoolService.sendReservationRequest(id,aula,row,col);
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

    public void refresh() {
        ServicesFactory.getSchoolService().refreshUser(id);
    }

    public void refreshUserParams(JSONObject response) {
        try {
            id = response.getString("id");
            name = response.getString("name");
            profileType = UserProfileType.getUserProfileFromString(response.getString("profile"));
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
        //schoolsID.add(0,school.getId());
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

    public void setNewSchoolID(String schoolId) {
        schoolsID.add(0,schoolId);
    }

    public void notifyPossibleContagion(MutableLiveData<ContagionRequestResult> result) {
        ServicesFactory.getContagionService().notifyPossibleContagion(id,result);
    }

    public void print() {
        System.out.println(name);
        System.out.println(id);
        System.out.println(risk);
        System.out.println(profileType);
        for (String k: schoolsID) System.out.println(k);
    }
}
