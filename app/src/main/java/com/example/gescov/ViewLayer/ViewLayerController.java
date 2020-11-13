package com.example.gescov.ViewLayer;

import com.example.gescov.DomainLayer.Classmodels.Classroom;
import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.Controllers.ModelController;
import com.example.gescov.ViewLayer.MainView.TokenVerificationResult;
import com.example.gescov.ViewLayer.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.ViewLayer.home.ContagionRequestResult;

import org.json.JSONException;

import java.util.List;

import androidx.lifecycle.MutableLiveData;


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

    public void notifyInfected(MutableLiveData<ContagionRequestResult> result) {
        modelController.notifyInfected(result);
    }

    public String getClassroomDimensions(String schoolId, String classroomId) {
        return modelController.getClassroomDimensions(schoolId, classroomId);
    }

    public void sendReservationRequest(String aula, int row, int col) {
        modelController.sendReservationRequest(aula,row,col);
    }

    public void createSchool( String schoolName, String schoolAddress, String schoolState, String schoolWebsite) {
        modelController.createSchool(schoolName, schoolAddress, schoolState, schoolWebsite);
    }

    public void createClassroom(School currentSchool, String classroomName, String classrooomCapacity, String classroomRows, String classroomCols) {
        modelController.createClassroom(currentSchool, classroomName, classrooomCapacity, classroomRows, classroomCols);
    }

    public void deleteSchool(School school) {
        modelController.deleteSchool(school.getId());
    }

    public List<Classroom> getSchoolClassrooms(String schoolName) {
        return modelController.getSchoolClassrooms(schoolName);
    }

    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult) {
        modelController.getStudentsInClassSession(studentsResult);
    }
  
    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result) {
        modelController.notifyRecovery(result);
    }
  
    public void sendAnswers(List<Boolean> answers) {
        modelController.sendAnswers(answers);
    }
  
    public void checkLoginUser(MutableLiveData<TokenVerificationResult> r) {
        modelController.checkLoginUser(r);
    }

    public void updateUserId(String userId) {
        modelController.updateUserId(userId);
    }

    public String getUserId() {
        return modelController.getUserId();
    }
}
