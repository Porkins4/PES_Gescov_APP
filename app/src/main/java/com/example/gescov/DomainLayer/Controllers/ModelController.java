package com.example.gescov.DomainLayer.Controllers;

import com.example.gescov.DomainLayer.Conection;

public class ModelController {

    private UserController userController;

    public ModelController() {
        userController = new UserController();
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

    public String getAllSchools() {
        return userController.getAllSchools();
    }

    public Boolean notifyInfected() {
        // para obtener la School
        return userController.notifyInfected();

    }

    public void sendReservationRequest(String aula, int row, int col) {
        userController.sendReservationRequest(aula,row,col);
    }

    public void createSchool(String schoolName, String schoolAddress) {
        userController.createSchool(schoolName,schoolAddress);
    }
}
