package com.example.gescov.DomainLayer.Controllers;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.Conection;
import com.example.gescov.DomainLayer.DomainControlFactory;

import org.json.JSONException;

import java.util.List;

public class ModelController {

    private UserController userController;

    public ModelController() {
        userController = DomainControlFactory.getUserController();
    }

    public String getAllContagions() {
       return userController.getContagionsOfMyCenter();
    }

    public void CreateUser(String nameuser) {
        userController = new UserController();
        userController.initUser();// remember to modify this when u have an user
    }


    //-1 means that user is not linked to the specified school
    public String getClassroomDimensions(String schoolId, String classroomId) {
        if (userController.containsSchool(schoolId)) return userController.getClassroomDimensions(schoolId, classroomId);
        return "-1";
    }

    public String getStudentsInClassroom(String classroom) {
        return userController.getStudentsInClassroom(classroom);
    }

    public List<School> getAllSchools() throws JSONException {
        String schoolsString = userController.getAllSchools();
        DomainControlFactory.getSchoolsModelCrontroller().setSchoolsList(schoolsString);
        return DomainControlFactory.getSchoolsModelCrontroller().getSchoolsList();
    }

    public void createSchool(String schoolId, String schoolName, String schoolAddress, String schoolState, String schoolCreator) {
        userController.createSchool(schoolName,schoolAddress);
    }

    public Boolean notifyInfected() {
        // para obtener la School
        return userController.notifyInfected();

    }

    public void sendReservationRequest(String aula, int row, int col) {
        userController.sendReservationRequest(aula,row,col);
    }

    public void createClassroom(School currentSchool, String classroomName, String classrooomCapacity, String classroomRows, String classroomCols) {
        currentSchool.createClassroom(classroomName, classrooomCapacity, classroomRows, classroomCols);
    }
}
