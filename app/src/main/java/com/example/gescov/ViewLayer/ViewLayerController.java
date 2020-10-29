package com.example.gescov.ViewLayer;

import com.example.gescov.DomainLayer.Controllers.ModelController;


public class ViewLayerController {
    private  ModelController modelController;

    public ViewLayerController() {
        modelController = new ModelController();
    }

    public String getStudentsInClassroom(String classroom) {
        return Mc.getStudentsInClassroom(classroom);
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
        return Mc.getClassroomDimensions(schoolId, classroomId);
    }
}
