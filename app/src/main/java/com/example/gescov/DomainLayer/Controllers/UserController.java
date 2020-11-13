package com.example.gescov.DomainLayer.Controllers;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.DomainLayer.Services.ISchoolService;
import com.example.gescov.DomainLayer.Services.ServicesFactory;
import com.example.gescov.ViewLayer.MainView.TokenVerificationResult;
import com.example.gescov.ViewLayer.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.ViewLayer.home.ContagionRequestResult;
import java.util.List;

public class UserController {
    private User user;

    public UserController() {
        user = new User();
    }

    public String getStudentsInClassroom(String classroom) {
        return user.getStudentsInClassroom(classroom);
    }

    public void initUser() {
        user = new User();
    }


    public String getContagionsOfMyCenter() {
        return user.getCntagionsOfCenter();
    }


    //stub at the moment
    public boolean containsSchool(String schoolId) {
        return true;
    }

    //el colegio es contenido por el usuario
    public String getClassroomDimensions(String schoolId, String classroomId) {
        return user.getClassroomDimensions(schoolId, classroomId);
    }

    public String getAllSchools() {
        return user.getAllSchools();
    }

    public void notifyInfected(MutableLiveData<ContagionRequestResult> result) {
        // el comportamineto seria obtener la escuela de nuestro usuario y pasarsela
        user.notifiyContagion(result);

    }

    public void sendReservationRequest(String aula, int row, int col) {
        user.sendReservationRequest(aula,row,col);
    }

    public void createSchool(String schoolName, String schoolAddress, String schoolState, String schoolWebsite) {
        user.createSchool(schoolName, schoolAddress, schoolState, schoolWebsite);
    }

    public void deleteSchool(String schoolId) {
        user.deleteSchool(schoolId);
    }

    public String getSchoolClassrooms(String schoolName) {
        return user.getSchoolClassrooms(schoolName);
    }


    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult) {
        ISchoolService iSchoolService = ServicesFactory.getSchoolService();
        iSchoolService.getStudentsInClassSession(studentsResult);
    }

    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result) {
        user.notifyRecovery(result);
    }

    public void sendAnswers(List<Boolean> answers) {
        user.sendAnswers(answers);
    }
  
    public void checkLoginUser(MutableLiveData<TokenVerificationResult> r) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        schoolService.checkUserLogin(r);
    }

    public void updateUserId(String userId) {
        user.setId(userId);
    }

    public String getUserId() {
        return user.getId();
    }

    public void updateUserName(String userName) {
        user.setName(userName);
    }

    public void setContagionId(String contagionId) {
        user.setIdContagion(contagionId);
        System.out.println(user.getIdContagion());
    }
}
