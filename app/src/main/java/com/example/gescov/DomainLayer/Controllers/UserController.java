package com.example.gescov.DomainLayer.Controllers;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.DomainLayer.DomainControlFactory;
import com.example.gescov.DomainLayer.Services.ISchoolService;
import com.example.gescov.DomainLayer.Services.ServicesFactory;
import com.example.gescov.ViewLayer.MainView.TokenVerificationResult;

import com.example.gescov.ViewLayer.SchoolClassroomList.SchoolRequestResult;

import com.example.gescov.ViewLayer.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.ViewLayer.home.ContagionRequestResult;

import org.json.JSONObject;

import java.util.List;

public class UserController {
    private User loggedUser;

    public UserController() {
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

    public void setContagionId(String contagionId) {
        loggedUser.setIdContagion(contagionId);
        System.out.println(loggedUser.getIdContagion());
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
}
