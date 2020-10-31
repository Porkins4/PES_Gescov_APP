package com.example.gescov.ViewLayer;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.Controllers.ModelController;

import org.json.JSONException;

import java.util.List;


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

    public List<School> getAllSchools() throws JSONException {
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

    public void createSchool(String schoolId, String schoolName, String schoolAddress, String schoolState, String schoolCreator) {
        modelController.createSchool(schoolId, schoolName, schoolAddress, schoolState, schoolCreator);
    }

    public void createClassroom(School currentSchool, String classroomName, String classrooomCapacity, String classroomRows, String classroomCols) {
        modelController.createClassroom(currentSchool, classroomName, classrooomCapacity, classroomRows, classroomCols);
    }
}
