package com.example.gescov.DomainLayer.Controllers;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.DomainLayer.Services.LoginRespository;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.ISchoolService;
import com.example.gescov.DomainLayer.Singletons.ServicesFactory;
import com.example.gescov.ViewLayer.SignUpAndLogin.TokenVerificationResult;

import com.example.gescov.ViewLayer.SchoolsActivities.SchoolClassroomList.SchoolRequestResult;

import com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.ViewLayer.Singletons.LoggedInUser;
import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;
import com.example.gescov.ViewLayer.home.ContagionRequestResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserModelController {
    private User loggedUser;

    public UserModelController() {
        loggedUser = new User();
    }

    public String getStudentsInClassroom(String classroom) {
        return loggedUser.getStudentsInClassroom(classroom);
    }

    public void initUser() {
        loggedUser = new User();
    }


    public String getContagionsOfMyCenter() {
        return loggedUser.getCntagionsOfCenter();
    }


    //stub at the moment
    public boolean containsSchool(String schoolId) {
        return true;
    }

    //el colegio es contenido por el usuario
    public String getClassroomDimensions(String schoolId, String classroomId) {
        return loggedUser.getClassroomDimensions(schoolId, classroomId);
    }

    public String getAllSchools() {
        return loggedUser.getAllSchools();
    }

    public void refreshSchoolList() {
        loggedUser.refreshSchoolList();
    }

    public void notifyInfected(MutableLiveData<ContagionRequestResult> result) {
        // el comportamineto seria obtener la escuela de nuestro usuario y pasarsela
        loggedUser.notifiyContagion(result);

    }

    public void sendReservationRequest(String aula, int row, int col) {
        loggedUser.sendReservationRequest(aula,row,col);
    }

    public void createSchool(String schoolName, String schoolAddress, String schoolTelephone, String schoolWebsite) {
        loggedUser.createSchool(schoolName, schoolAddress, schoolTelephone, schoolWebsite);
    }

    public void deleteSchool(String schoolId) {
        loggedUser.deleteSchool(schoolId);
    }

    public void refreshSchoolClassrooms(String schoolName) {
        loggedUser.getSchoolClassrooms(schoolName);
    }


    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult) {
        ISchoolService iSchoolService = ServicesFactory.getSchoolService();
        iSchoolService.getStudentsInClassSession(studentsResult);
    }

    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result) {
        loggedUser.notifyRecovery(result);
    }

    public void sendAnswers(List<Boolean> answers) {
        loggedUser.sendAnswers(answers);
    }
  
    public void checkLoginUser(MutableLiveData<TokenVerificationResult> r) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        schoolService.checkUserLogin(r);
    }

    public void updateUserId(String userId) {
        loggedUser.setId(userId);
    }

    public String getUserId() {
        return loggedUser.getId();
    }

    public void updateUserName(String userName) {
        loggedUser.setName(userName);
    }

    public void updateClassroom(String classroomId, String classroomName, int numRows, int numCols) {
        loggedUser.updateSchoolClassroom(classroomId, classroomName, numRows, numCols, numRows*numCols);

    }

    public void deleteClassroom(String classroomId) {
        loggedUser.deleteSchoolClassroom(classroomId);
    }

    public void getTypeProfile() {
        loggedUser.getTypeProfile();
    }

    public void setUserProfile(JSONObject response) {
        loggedUser.refreshUserParams(response);
        DomainControlFactory.getModelController().updateHomeViewModel(loggedUser.getName(), loggedUser.getRisk());
    }

    public void addStudentToCenter(School school, MutableLiveData<SchoolRequestResult> result) {
        loggedUser.addStudentToCenter(school,result);
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void updateLoggedUserRisk() {
        loggedUser.updateRisk();
    }

    public void changeUserProfile(String profile) {
        loggedUser.changeUSerProfile(profile);
    }

    public void setUserType(String profile) {
        loggedUser.setProfileType(profile);
    }

    public String getProfileType() {
        return loggedUser.getProfileType();
    }

    public void setNewSchoolID(String schoolId) {
        loggedUser.setNewSchoolID(schoolId);
    }

    public void notifyPossibleContagion(MutableLiveData<ContagionRequestResult> result) {
        loggedUser.notifyPossibleContagion(result);
    }


    //-----------------------------------------------
    //login operations
    public void setUserLoggedIn(String serverClientID) {
        LoginRespository.checkuserAlreadyLoggedInOnthisDevice(serverClientID);
    }

    public void getUserID() {
        ServicesFactory.getUserService().getUserID(LoggedInUser.getToken());
    }

    public void setUserID(Boolean error, String userID) {
        if (!error) {
            loggedUser.setId(userID);
        }
        DomainControlFactory.getModelController().setUserIDVerificationResult(error);
    }

    public void retrieveUserInformation() {
        ServicesFactory.getUserService().getUserInfo(loggedUser.getId());
    }

    public void setLoggedInUser(Boolean error, JSONObject response) {
        if (!error){
            try {
                JSONArray schoolsRaw = response.getJSONArray("schoolsID");
                List<String> ls = new ArrayList<>();
                for (int i = 0; i < schoolsRaw.length(); ++i) {
                    ls.add(schoolsRaw.getString(i));
                }
                loggedUser = new User(response.getString("name"),ls,response.getString("id"),response.getBoolean("risk"), response.getString("profile"));
                updateContagionId();
            } catch (JSONException e) {
            }
        }
    }

    public User getLoggedInUser() {
        return loggedUser;
    }

    public void updateContagionId() {
        ServicesFactory.getContagionService().getContagionID(loggedUser.getId());
    }

    public void setContagionId(String contagionId, Boolean error) {
        loggedUser.setIdContagion(contagionId);
        DomainControlFactory.getModelController().setUserRetrieveResult(error);
    }

    public void updateContagionID(String contagionId) {
        loggedUser.setIdContagion(contagionId);
    }
}
