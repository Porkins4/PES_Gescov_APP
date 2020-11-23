package com.example.gescov.DomainLayer.Controllers;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.Classmodels.Assignment;
import com.example.gescov.DomainLayer.Classmodels.Classroom;
import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;
import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;
import com.example.gescov.ViewLayer.SchoolsActivities.SchoolClassroomList.SchoolRequestResult;
import com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.ViewLayer.ViewLayerController;
import com.example.gescov.ViewLayer.home.ContagionRequestResult;
import com.example.gescov.ViewLayer.SignUpAndLogin.TokenVerificationResult;
import org.json.JSONException;

import java.util.List;

public class ModelController {

    private UserModelController userModelController;
    private ViewLayerController viewLayerController;

    public ModelController() {
        userModelController = DomainControlFactory.getUserModelController();
        viewLayerController = PresentationControlFactory.getViewLayerController();

    }

    public String getAllContagions() {
       return userModelController.getContagionsOfMyCenter();
    }

    public void CreateUser(String nameuser) {
        userModelController = new UserModelController();
        userModelController.initUser();// remember to modify this when u have an user
    }


    //-1 means that user is not linked to the specified school
    public String getClassroomDimensions(String schoolId, String classroomId) {
        if (userModelController.containsSchool(schoolId)) return userModelController.getClassroomDimensions(schoolId, classroomId);
        return "-1";
    }

    public String getStudentsInClassroom(String classroom) {
        return userModelController.getStudentsInClassroom(classroom);
    }

    public void getAllSchools() throws JSONException {
        DomainControlFactory.getSchoolsModelCrontroller().refreshSchoolList();
    }

    public void createSchool(String schoolName, String schoolAddress, String schoolTelephone, String schoolWebsite) {
        DomainControlFactory.getSchoolsModelCrontroller().createSchool(schoolName, schoolAddress, schoolTelephone, schoolWebsite);
    }

    public void notifyInfected(MutableLiveData<ContagionRequestResult> result) {
        // para obtener la School
        userModelController.notifyInfected(result);

    }

    public void sendReservationRequest(String aula, int row, int col) {
        userModelController.sendReservationRequest(aula,row,col);
    }

    public void createClassroom(School currentSchool, String classroomName, int classroomRows, int classroomCols) {
        currentSchool.createClassroom(classroomName, classroomRows, classroomCols);
    }

    public void updateClassroom(String classroomId, String classroomName, int numRows, int numCols) {
        userModelController.updateClassroom(classroomId, classroomName, numRows, numCols);
    }

    public void daleteClassroom(String classroomId) {
        userModelController.deleteClassroom(classroomId);
    }

    public void deleteSchool(String schoolId) {
        userModelController.deleteSchool(schoolId);
    }

    public void getSchoolClassrooms(String schoolName) {
        userModelController.refreshSchoolClassrooms(schoolName);
    }


    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult) {
        userModelController.getStudentsInClassSession(studentsResult);
    }
  
    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result) {
        userModelController.notifyRecovery(result);
    }
  
    public void sendAnswers(List<Boolean> answers) {
        userModelController.sendAnswers(answers);
    }

    public void checkLoginUser(MutableLiveData<TokenVerificationResult> r) {
        userModelController.checkLoginUser(r);
    }

    public void updateUserId(String userId) {
        userModelController.updateUserId(userId);
    }

    public String getUserId() {
        return userModelController.getUserId();
    }

    public void updateUserName(String userName) {
        userModelController.updateUserName(userName);
    }

    public void setContagionId(String contagionId) {
        userModelController.setContagionId(contagionId);
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

    public void getTypeProfile() {
        DomainControlFactory.getUserModelController().getTypeProfile();
    }

    public void addStudentToCenter(String schoolName, MutableLiveData<SchoolRequestResult> result) {
       School school = DomainControlFactory.getSchoolsModelCrontroller().getSchoolByName(schoolName);
       userModelController.addStudentToCenter(school,result);
    }

    public void changeUserProfile(String profile) {
        userModelController.changeUserProfile(profile);
    }

    public String getUserType() {
        return userModelController.getProfileType();
    }

    public void getStudentsInClassRecord(String classroomId, String date) {
        DomainControlFactory.getClassroomModelController().getStudentsInClassRecord(classroomId,date);
    }

    public void refreshStudentsInClassRecordView(List<Pair<User, Pair<Integer,Integer>>> r, boolean b) {
        viewLayerController.refreshStudentsInClassRecordView(r,b);
    }

    public User getLoggedUser() {
       return DomainControlFactory.getUserModelController().getLoggedUser();
    }

    public void updateLoggedUserRisk() {
        DomainControlFactory.getUserModelController().updateLoggedUserRisk();
    }

    public void updateHomeViewModel(String name, boolean risk) {
        //PresentationControlFactory.getViewLayerController().updateHomeViewModel(name, risk);
    }

    public void notifyPossibleContagion(MutableLiveData<ContagionRequestResult> result) {
        DomainControlFactory.getUserModelController().notifyPossibleContagion(result);
    }

    public void getAssignmentsForClassSession(String classroomID, String date, String hour) {
        DomainControlFactory.getAssignmentModelController().getAssignmentsForClassSession(classroomID,date,hour);
    }

    public void refreshClassroomDistributionAssignments(List<Assignment> r, boolean b) {
        PresentationControlFactory.getViewLayerController().refreshClassroomDistributionAssignments(r,b);
    }

    public void getClassroomInfo(String classroomID) {
        DomainControlFactory.getClassroomModelController().getClassroomInfo(classroomID);
    }

    public void refreshClassroomDistributionClassInfo(Classroom c, boolean b) {
        PresentationControlFactory.getViewLayerController().refreshClassroomDistributionClassInfo(c,b);
    }
}
