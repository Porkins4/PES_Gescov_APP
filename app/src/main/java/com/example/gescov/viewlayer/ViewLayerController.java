package com.example.gescov.viewlayer;

import android.util.Pair;

import com.example.gescov.DomainLayer.Classmodels.Assignment;
import com.example.gescov.DomainLayer.Classmodels.Chat;
import com.example.gescov.DomainLayer.Classmodels.Classroom;
import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.Classmodels.SchoolRequest;
import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;
import com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.viewlayer.Exceptions.AdapterNotSetException;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolRequestResult;
import com.example.gescov.viewlayer.SignUpAndLogin.TokenVerificationResult;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.home.ContagionRequestResult;
import com.example.gescov.viewlayer.home.HomeViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

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

    public void refreshAllSchools() throws JSONException {
        DomainControlFactory.getModelController().refreshAllSchools();
    }

    public void refreshStudentSchools() {
        DomainControlFactory.getModelController().refreshStudentSchools();
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


    public void deleteClassroom(String classroomId) {
        DomainControlFactory.getModelController().daleteClassroom(classroomId);
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

    public void addStudentToCenter(String schoolName, MutableLiveData<SchoolRequestResult> result) {
        DomainControlFactory.getModelController().addStudentToCenter(schoolName,result);
    }

    public void changeUserProfile(boolean isStudent) {
        DomainControlFactory.getModelController().changeUserProfile(isStudent);
    }

    public User.UserProfileType getUserType() {
        return DomainControlFactory.getModelController().getUserType();
    }

    public void getStudentsInClassRecord(String classroomId, String date) {
        DomainControlFactory.getModelController().getStudentsInClassRecord(classroomId,date);
    }

    public void refreshStudentsInClassRecordView(List<Pair<User, Pair<Integer,Integer>>> r, boolean b) {
        PresentationControlFactory.getStudentsInClassSessionController().refreshStudentsInClassRecordView(r,b);
    }

    public User getLoggedUserInfo() {
        return DomainControlFactory.getModelController().getLoggedUser();
    }

    public void updateLoggedUserRisk() {
        DomainControlFactory.getModelController().updateLoggedUserRisk();
    }

    public void updateHomeViewModel(String name, boolean risk) {
      HomeViewModel viewModel = PresentationControlFactory.getViewModelProvider().get(HomeViewModel.class);
      viewModel.setName(name);
      viewModel.setRisk(risk);
    }

    public void notifyPossibleContagion(MutableLiveData<ContagionRequestResult> result) {
        DomainControlFactory.getModelController().notifyPossibleContagion(result);
    }

    public void getAssignmentsForClassSession(String classroomID, String date, String hour) {
        DomainControlFactory.getModelController().getAssignmentsForClassSession(classroomID,date,hour);
    }

    public void refreshClassroomDistributionAssignments(List<Assignment> r, boolean b) {
        PresentationControlFactory.getStudentsInClassSessionController().refreshClassroomDistributionAssignments(r,b);
    }

    public void getClassroomInfo(String classroomID) {
        DomainControlFactory.getModelController().getClassroomInfo(classroomID);
    }

    public void refreshClassroomDistributionClassInfo(Classroom c, boolean b) {
        PresentationControlFactory.getStudentsInClassSessionController().refreshClassroomDistributionClassInfo(c,b);
    }

    //------------------------------------------------
    //login
    public void setLoginResult(boolean result) {
        PresentationControlFactory.getLoadingProfileController().setLoginResult(result);
    }

    public void getUserID() {
        DomainControlFactory.getModelController().getUserID();
    }

    public void setUserIDVerificationResult(boolean error) {
        PresentationControlFactory.getLoadingProfileController().setUserIDVerificationResult(error);
    }

    public void setUserLoggedIn(String serverClientID) {
        DomainControlFactory.getModelController().setUserLoggedIn(serverClientID);
    }

    public void retrieveUserInformation() {
        DomainControlFactory.getModelController().retrieveUserInformation();
    }

    public void setUserRetrieveResult(boolean error) {
        PresentationControlFactory.getLoadingProfileController().setUserRetrieveResult(error);
    }

    public User getUserLoggedIn() {
        return DomainControlFactory.getModelController().getLoggedInUser();
    }

    public GoogleSignInClient getGoogleSignInClient(String serverClientID) {
        return DomainControlFactory.getModelController().getGoogleSignInClient(serverClientID);
    }

    public void getNumContagionPerSchool(int from  ) {
        DomainControlFactory.getModelController().getNumContagionPerSchool(from);
    }

    public void sendResponseOfNumContagionPerSchool(List<Pair<School, Integer>> schools, int from) {
        if (from == 1) {
            PresentationControlFactory.getMapController().sendResponseOfNumContagionPerSchool(schools);
        }
        else PresentationControlFactory.getRankingController().sendResponseOfNumContagionPerSchool(schools);

    }


    public void refreshSchoolRequests(List<SchoolRequest> schoolRequestsList) {
        PresentationControlFactory.getSchoolRequestsController().refreshList(schoolRequestsList);
    }

    //---------------------------------
    //update user schools
    public void updateSchools() {
        DomainControlFactory.getModelController().updateSchools();
    }

    public void notifySchoolsReceivedToCreateChatActivity() {
        PresentationControlFactory.getCreateChatController().notifySchoolsReceivedToCreateChatActivity();
    }

    public List<School> getUserSchools() {
        return DomainControlFactory.getModelController().getUserSchools();
    }

    public void getContactsFromCenter(String schoolID) {
        DomainControlFactory.getModelController().getContactsFromCenter(schoolID);
    }

    public void updateContactsFromCreateChat() {
        PresentationControlFactory.getCreateChatController().updateContactsFromCreateChat();
    }

    public List<User> getContacts() {
        return DomainControlFactory.getModelController().getContacts();

    }

    public void refreshUsersListBySchoolId() {
        DomainControlFactory.getSchoolsModelCrontroller().refreshUsersListBySchoolId();
    }

    public void refreshSchoolUsersList(List<User> usersList) {
        PresentationControlFactory.getSchoolUsersController().refreshList(usersList);
    }

    public void addNewAdminToSchool(String newAdminID) {
        DomainControlFactory.getSchoolsModelCrontroller().addNewAdminToSchool(newAdminID);
    }


    //----------------------------------
    //Chats
    public void createChat(String targetID) {
        DomainControlFactory.getModelController().createChat(targetID);
    }

    public void chatCreatedInBack(Chat chat, boolean error) {
        PresentationControlFactory.getCreateChatController().chatCreatedInBack(chat, error);
    }
}
