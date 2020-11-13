package com.example.gescov.DomainLayer.Controllers;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.DomainLayer.Services.ISchoolService;
import com.example.gescov.DomainLayer.Services.ServicesFactory;
import com.example.gescov.ViewLayer.MainView.TokenVerificationResult;

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
    public void initUser(String nameuser) {
        user = new User(nameuser);
        System.out.println(user.getName());
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

    public Boolean notifyInfected() {
        // el comportamineto seria obtener la escuela de nuestro usuario y pasarsela
        return user.notifiyContagion();

    }

    public void sendReservationRequest(String aula, int row, int col) {
        user.sendReservationRequest(aula,row,col);
    }

    public void createSchool(String schoolName, String schoolAddress) {
        user.createSchool(schoolName,schoolAddress);
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
}
