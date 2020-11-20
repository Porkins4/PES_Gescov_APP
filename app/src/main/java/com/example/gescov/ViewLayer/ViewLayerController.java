package com.example.gescov.ViewLayer;

import com.example.gescov.DomainLayer.Classmodels.Classroom;
import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.Controllers.ModelController;
import com.example.gescov.DomainLayer.DomainControlFactory;
import com.example.gescov.ViewLayer.Exceptions.AdapterNotSetException;
import com.example.gescov.ViewLayer.MainView.TokenVerificationResult;
import com.example.gescov.ViewLayer.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.ViewLayer.home.ContagionRequestResult;

import org.json.JSONException;

import java.util.List;

import androidx.lifecycle.MutableLiveData;


public class ViewLayerController {

    public ViewLayerController() {
    }

    public String getStudentsInClassroom(String classroom) {
        return DomainControlFactory.getModelController().getStudentsInClassroom(classroom);
    }

    public String getAllContagions() {
        return DomainControlFactory.getModelController().getAllContagions();
    }

    public void getAllSchools() throws JSONException {
        DomainControlFactory.getModelController().getAllSchools();
    }

    public void notifyInfected(MutableLiveData<ContagionRequestResult> result) {
        DomainControlFactory.getModelController().notifyInfected(result);
    }

    public String getClassroomDimensions(String schoolId, String classroomId) {
        return DomainControlFactory.getModelController().getClassroomDimensions(schoolId, classroomId);
    }

    public void sendReservationRequest(String aula, int row, int col) {
        DomainControlFactory.getModelController().sendReservationRequest(aula,row,col);
    }

    public void createSchool( String schoolName, String schoolAddress, String schoolTelephone, String schoolWebsite) {
        DomainControlFactory.getModelController().createSchool(schoolName, schoolAddress, schoolTelephone, schoolWebsite);
    }

    public void createClassroom(School currentSchool, String classroomName, int classroomRows, int classroomCols) {
        DomainControlFactory.getModelController().createClassroom(currentSchool, classroomName, classroomRows, classroomCols);
    }

    public void updateClassroom(String classroomid, String classroomName, int numRows, int numCols) {
        DomainControlFactory.getModelController().updateClassroom(classroomid, classroomName, numRows, numCols);
    }

    public void deleteSchool(School school) {
        DomainControlFactory.getModelController().deleteSchool(school.getId());
    }

    public void getSchoolClassrooms(String schoolName) {
        DomainControlFactory.getModelController().getSchoolClassrooms(schoolName);
    }

    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult) {
        DomainControlFactory.getModelController().getStudentsInClassSession(studentsResult);
    }
  
    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result) {
        DomainControlFactory.getModelController().notifyRecovery(result);
    }
  
    public void sendAnswers(List<Boolean> answers) {
        DomainControlFactory.getModelController().sendAnswers(answers);
    }
  
    public void checkLoginUser(MutableLiveData<TokenVerificationResult> r) {
        DomainControlFactory.getModelController().checkLoginUser(r);
    }

    public void updateUserId(String userId) {
        DomainControlFactory.getModelController().updateUserId(userId);
    }

    public String getUserId() {
        return DomainControlFactory.getModelController().getUserId();
    }

    public void updateUserName(String userName) {
        DomainControlFactory.getModelController().updateUserName(userName);
    }

    public void setContagionId(String contagionId) {
        DomainControlFactory.getModelController().setContagionId(contagionId);
    }

    public void refreshSchoolList(List<School> schoolsList) {
        try {
            PresentationControlFactory.getSchoolsCrontroller().refreshSchoolsList(schoolsList);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (AdapterNotSetException e) {
            e.printStackTrace();
        }
    }

    public void refreshSchoolClassroomList(List<Classroom> classroomsList) {
        PresentationControlFactory.getClassroomsCrontroller().refreshList(classroomsList);
    }

    public void setCurrentSchool(School currentSchool) {
        DomainControlFactory.getModelController().setCurrentSchool(currentSchool);
    }

    public void getTypeProfile() {
        DomainControlFactory.getModelController().getTypeProfile();
    }

    public void addStudentToCenter(String schoolName) {
        DomainControlFactory.getModelController().addStudentToCenter(schoolName);
    }
}
