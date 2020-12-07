package com.example.gescov.domainlayer.Controllers;

import android.util.Pair;

import com.example.gescov.domainlayer.Classmodels.Assignment;
import com.example.gescov.domainlayer.Classmodels.Chat;
import com.example.gescov.domainlayer.Classmodels.ChatPreviewModel;
import com.example.gescov.domainlayer.Classmodels.Classroom;
import com.example.gescov.domainlayer.Classmodels.MessageModel;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.SchoolRequest;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolRequestResult;
import com.example.gescov.viewlayer.SignUpAndLogin.TokenVerificationResult;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.ViewLayerController;
import com.example.gescov.viewlayer.home.ContagionRequestResult;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import org.json.JSONException;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class ModelController {

    private UserModelController userModelController;
    private ViewLayerController viewLayerController;

    public ModelController() {
        userModelController = DomainControlFactory.getUserModelController();
        viewLayerController = PresentationControlFactory.getViewLayerController();

    }

    public String getAllContagions(String schoolID) {
       return userModelController.getContagionsOfMyCenter(schoolID);
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

    public void refreshAllSchools() throws JSONException {
        DomainControlFactory.getSchoolsModelCrontroller().refreshSchoolList();
    }

    public void refreshStudentSchools() {
        String userId = getUserId();
        DomainControlFactory.getSchoolsModelCrontroller().refreshStudentSchools(userId);
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
        DomainControlFactory.getUserModelController().refreshLoggedUser();
    }

    public void addStudentToCenter(String schoolName, MutableLiveData<SchoolRequestResult> result) {
       School school = DomainControlFactory.getSchoolsModelCrontroller().getSchoolByName(schoolName);
       userModelController.addStudentToCenter(school,result);
    }

    public void changeUserProfile(boolean isStudent) {
        userModelController.changeUserProfile(isStudent);
    }

    public User.UserProfileType getUserType() {
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

    //-----------------------------------------------
    //login access

    public void setUserLoggedIn(String serverClientID) {
        DomainControlFactory.getUserModelController().setUserLoggedIn(serverClientID);
    }

    public void setLoginResul(boolean result) {
        PresentationControlFactory.getViewLayerController().setLoginResult(result);
    }

    public void getUserID() {
        DomainControlFactory.getUserModelController().getUserID();
    }

    public void setUserIDVerificationResult(boolean error) {
        PresentationControlFactory.getViewLayerController().setUserIDVerificationResult(error);
    }

    public void retrieveUserInformation() {
        DomainControlFactory.getUserModelController().retrieveUserInformation();
    }

    public void setUserRetrieveResult(Boolean error) {
        PresentationControlFactory.getViewLayerController().setUserRetrieveResult(error);
    }

    public User getLoggedInUser() {
        return DomainControlFactory.getUserModelController().getLoggedInUser();
    }

    public GoogleSignInClient getGoogleSignInClient(String serverClientID) {
        return DomainControlFactory.getUserModelController().getGoogleSignInClient(serverClientID);
    }
    //-----------------------------------------------------------------

    public void getNumContagionPerSchool(int from) {
        DomainControlFactory.getSchoolsModelCrontroller().getNumContagionPerSchool(from);
    }

    public void sendResponseOfNumContagionPerSchool(List<Pair<School, Integer>> schools, int from) {
        viewLayerController.sendResponseOfNumContagionPerSchool(schools,from);

    }

    public void refreshSchoolRequestsInView(List<SchoolRequest> schoolRequestsList) {
        viewLayerController.refreshSchoolRequests(schoolRequestsList);
    }

    //-----------------------------------------------------------------
    //Update Schools from a user
    public void updateSchools() {
        DomainControlFactory.getUserModelController().updateSchools();
    }

    public void notifySchoolsReceivedToCreateChatActivity() {
        PresentationControlFactory.getViewLayerController().notifySchoolsReceivedToCreateChatActivity();
    }

    public List<School> getUserSchools() {
        return DomainControlFactory.getSchoolsModelCrontroller().getUserSchools();
    }

    public void getContactsFromCenter(String schoolID) {
        DomainControlFactory.getSchoolsModelCrontroller().getContactsFromCenter(schoolID);
    }

    public void updateContactsFromCreateChat() {
        PresentationControlFactory.getViewLayerController().updateContactsFromCreateChat();
    }

    public List<User> getContacts() {
        return DomainControlFactory.getUserModelController().getContacts();
    }

    public void refreshSchoolUsersListInView(List<User> usersList) {
        viewLayerController.refreshSchoolUsersList(usersList);
    }


    public void setGraph(String schoolId) {
        DomainControlFactory.getSchoolsModelCrontroller().setGraph(schoolId);
    }

    public void sendResponseOfGraph(List<Pair<String, Integer>> contagionPerMonth) {
        viewLayerController.sendResponseOfGraph(contagionPerMonth);
    }

    //------------------------------------
    //Create Chat
    public void createChat(String targetID) {
        DomainControlFactory.getChatModelController().createChat(targetID);
    }

    public void chatCreatedInBack(Chat chat, boolean error) {
        PresentationControlFactory.getViewLayerController().chatCreatedInBack(chat, error);
    }
  
    public void updateChatPreview() {
        DomainControlFactory.getChatModelController().updateChatPreview();
    }

    public void chatPreviewsUpdated(List<ChatPreviewModel> chatPreviewModels, boolean error) {
        PresentationControlFactory.getViewLayerController().chatPreviewsUpdated(chatPreviewModels, error);
    }

    public void getChatMessages(String chatID) {
        DomainControlFactory.getChatModelController().getMessages(chatID);
    }

    public void notifyChatMessagesResponse(List<MessageModel> messages, boolean error) {
        PresentationControlFactory.getViewLayerController().notifyChatMessagesResponse(messages,error);
    }

    public void sendMessage(String chatID, String message) {
        DomainControlFactory.getChatModelController().sendMessage(chatID,message);
    }

    public void notifyChatUpdated() {
        PresentationControlFactory.getViewLayerController().notifyChatUpdated();
    }

    public void deleteSchoolAdmin(String adminID) {
        DomainControlFactory.getSchoolsModelCrontroller().deleteSchoolAdmin(adminID);
    }

    public void currentSchoolRefreshed() {
        PresentationControlFactory.getViewLayerController().currentSchoolRefreshed();
    }

    public String getIdContagion() {
        return DomainControlFactory.getUserModelController().getIdContagion();
    }
}
