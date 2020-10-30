package com.example.gescov.ViewLayer;

import com.example.gescov.DomainLayer.Controllers.ModelController;


public class ViewLayerController {
    private  ModelController modelController;

    public ViewLayerController() {
        modelController = new ModelController();
    }

    public String getStudentsInClassroom(String classroom) {
        return modelController.getStudentsInClassroom(classroom);
    }

    public String getAllContagions() {
       return modelController.getAllContagions();
    }

    public String getAllSchools() {
       return modelController.getAllSchools();
    }

    public Boolean notifyInfected() {
        return modelController.notifyInfected();
    }

    public String getClassroomDimensions(String schoolId, String classroomId) {
        return modelController.getClassroomDimensions(schoolId, classroomId);
    }

    public void sendReservationRequest(String aula, int row, int col) {
        modelController.sendReservationRequest(aula,row,col);
    }

    public void createSchool(String schoolName, String schoolAddress) {
        modelController.createSchool(schoolName,schoolAddress);
    }
}
