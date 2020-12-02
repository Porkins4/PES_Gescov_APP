package com.example.gescov.DomainLayer.Classmodels;

import com.example.gescov.DomainLayer.Services.Volley.Interfaces.IContagionService;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.ISchoolService;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;
import com.example.gescov.DomainLayer.Singletons.ServicesFactory;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolRequestResult;
import com.example.gescov.viewlayer.home.ContagionRequestResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class User {

    public enum UserProfileType {
        STUDDENT ("Student"),
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

        public static UserProfileType getUserProfileFromBoolean(boolean isStudent) {
            return isStudent ? STUDDENT : TEACHER;
        }

        public String getText() {
            return value;
        }

    }

    private String name;
    private List<String> schoolsID;
    private String id;
    private String email;
    private String idContagion;
    private String ConfirmedInfected;
    private Boolean risk;
    private UserProfileType profileType;
    private String tokenId;

    public UserProfileType getProfileType() {
        return profileType;
    }


    public void setProfileType(String profileType) {
        this.profileType = UserProfileType.getUserProfileFromString(profileType);
    }

    public void setProfileType(boolean isStudent) {
        this.profileType = UserProfileType.getUserProfileFromBoolean(isStudent);
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

    public User (String name, String id, List<String> schools, boolean risk, boolean isStudent, String email, String tokenId) {
        this.name = name;
        this.schoolsID =  schools;
        this.id = id;
        this.risk = risk;
        this.profileType = UserProfileType.getUserProfileFromBoolean(isStudent);
        this.tokenId = tokenId;
        this.email = email;
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

    public void updateSchoolClassroom(String classroomId, String classroomName, int numRows, int numCols) {
        ServicesFactory.getUpdateSchoolClassroomController().updateSchoolClassroom(classroomId, classroomName, numRows, numCols);
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

    public void changeUserProfile(boolean isStudent) {
        ServicesFactory.getUserService().changeUserProfile(id, isStudent);
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

    public static User fromJSONtoUser(JSONObject jsonObject) {
        User response = new User();
        response.setId("null");
        try {
            String name = jsonObject.getString("name");
            String id = jsonObject.getString("id");
            String profileType = jsonObject.getString("profile");
            String email = jsonObject.getString("email");
            Boolean risk = jsonObject.getBoolean("risk");
            List<String> schools = new ArrayList<>();
            JSONArray a = jsonObject.getJSONArray("schoolsID");
            for (int i = 0; i < a.length(); i++) {
                schools.add(a.getString(i));
            }
            response.setId(id);
            response.setName(name);
            response.setProfileType(profileType);
            response.setSchoolsID(schools);
            response.setEmail(email);
            response.setRisk(risk);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void setEmail(String email) {
        this.email = email;
    }
}
