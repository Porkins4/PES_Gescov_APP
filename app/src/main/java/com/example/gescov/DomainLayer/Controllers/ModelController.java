package com.example.gescov.DomainLayer.Controllers;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.Classmodels.Classroom;
import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.DomainControlFactory;
import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.ViewLayer.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.ViewLayer.ViewLayerController;
import com.example.gescov.ViewLayer.home.ContagionRequestResult;
import com.example.gescov.ViewLayer.MainView.TokenVerificationResult;
import org.json.JSONException;

import java.util.List;

public class ModelController {

    private UserController userController;
    private ViewLayerController viewLayerController;

    public ModelController() {
        userController = DomainControlFactory.getUserController();
        viewLayerController = PresentationControlFactory.getViewLayerController();

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

    public void getAllSchools() throws JSONException {
        DomainControlFactory.getSchoolsModelCrontroller().refreshSchoolList();
    }

    public void createSchool(String schoolName, String schoolAddress, String schoolState, String schoolWebsite) {
        DomainControlFactory.getSchoolsModelCrontroller().createSchool(schoolName, schoolAddress, schoolState, schoolWebsite);
    }

    public void notifyInfected(MutableLiveData<ContagionRequestResult> result) {
        // para obtener la School
        userController.notifyInfected(result);

    }

    public void sendReservationRequest(String aula, int row, int col) {
        userController.sendReservationRequest(aula,row,col);
    }

    public void createClassroom(School currentSchool, String classroomName, String classrooomCapacity, String classroomRows, String classroomCols) {
        currentSchool.createClassroom(classroomName, classrooomCapacity, classroomRows, classroomCols);
    }

    public void deleteSchool(String schoolId) {
        userController.deleteSchool(schoolId);
    }

    public void getSchoolClassrooms(String schoolName) {
        userController.refreshSchoolClassrooms(schoolName);
    }


    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult) {
        userController.getStudentsInClassSession(studentsResult);
    }
  
    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result) {
        userController.notifyRecovery(result);
    }
  
    public void sendAnswers(List<Boolean> answers) {
        userController.sendAnswers(answers);
    }

    public void checkLoginUser(MutableLiveData<TokenVerificationResult> r) {
        userController.checkLoginUser(r);
    }

    public void updateUserId(String userId) {
        userController.updateUserId(userId);
    }

    public String getUserId() {
        return userController.getUserId();
    }

    public void updateUserName(String userName) {
        userController.updateUserName(userName);
    }

    public void setContagionId(String contagionId) {
        userController.setContagionId(contagionId);
    }

    public void refreshSchoolListInView(List<School> schoolsList) {
        viewLayerController.refreshSchoolList(schoolsList);
    }

    public void refreshSchoolClassroomsListInView(List<Classroom> classroomsList) {
        viewLayerController.refreshSchoolClassroomList(classroomsList);
    }

    public void setCurrentSchool(School currentSchool) {
        DomainControlFactory.getSchoolsModelCrontroller().setCurrentSchool(currentSchool);
    }
}
