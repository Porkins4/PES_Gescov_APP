package com.example.gescov.domainlayer.Controllers;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.domainlayer.Classmodels.Assignment;
import com.example.gescov.domainlayer.Classmodels.Chat;
import com.example.gescov.domainlayer.Classmodels.ChatPreviewModel;
import com.example.gescov.domainlayer.Classmodels.ClassSessionModel;
import com.example.gescov.domainlayer.Classmodels.Classroom;
import com.example.gescov.domainlayer.Classmodels.MessageModel;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.SchoolRequest;
import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.domainlayer.Classmodels.TracingTest;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.domainlayer.Classmodels.WallEntry;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.domainlayer.Singletons.LoginRespository;
import com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolRequestResult;
import com.example.gescov.viewlayer.SignUpAndLogin.TokenVerificationResult;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.ViewLayerController;
import com.example.gescov.viewlayer.home.ContagionRequestResult;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import org.json.JSONException;

import java.util.List;

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

    public void createSchool(String schoolName, String schoolAddress, String schoolTelephone, String schoolWebsite, String latitude, String longitude) {
        DomainControlFactory.getSchoolsModelCrontroller().createSchool(schoolName, schoolAddress, schoolTelephone, schoolWebsite, latitude, longitude);
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


    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult, String classSession) {
        userModelController.getStudentsInClassSession(studentsResult, classSession);
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

    public void addStudentToCenter(String schoolId, MutableLiveData<SchoolRequestResult> result) {
       userModelController.addStudentToCenter(schoolId,result);
    }

    public void changeUserProfile(boolean isStudent) {
        userModelController.changeUserProfile(isStudent);
    }

    public User.UserProfileType getUserType() {
        return userModelController.getProfileType();
    }

    public void getStudentsInClassRecord(String classroomId) {
        DomainControlFactory.getClassroomModelController().getStudentsInClassRecord(classroomId);
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

    public void getAssignmentsForClassSession(String classSessionID) {
        DomainControlFactory.getAssignmentModelController().getAssignmentsForClassSession(classSessionID);
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

    public void refreshLoggedUser() {
        DomainControlFactory.getUserModelController().refreshLoggedUser();
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

    public void getContactsFromCenter(String schoolID, int activityIdentifier) {
        DomainControlFactory.getSchoolsModelCrontroller().getContactsFromCenter(schoolID, activityIdentifier);
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


    public void notifyChatMessagesResponse(List<MessageModel> messages, boolean error) {
        PresentationControlFactory.getViewLayerController().notifyChatMessagesResponse(messages,error);
    }

    public void sendMessage(String chatID, String message) {
        DomainControlFactory.getChatModelController().sendMessage(chatID,message);
    }

    public void deleteSchoolAdmin(String adminID) {
        DomainControlFactory.getSchoolsModelCrontroller().deleteSchoolAdmin(adminID);
    }

    public void currentSchoolRefreshed() {
        PresentationControlFactory.getViewLayerController().currentSchoolRefreshed();
    }

    public School getCurrentSchool() {
       return DomainControlFactory.getSchoolsModelCrontroller().getCurrentSchool();
    }

    public String getIdContagion() {
        return DomainControlFactory.getUserModelController().getIdContagion();
    }

    public void updateCoordinatesSchoolCreationForm(String latitude, String longitude) {
        PresentationControlFactory.getViewLayerController().updateCoordinatesSchoolCreationForm(latitude, longitude);
    }

    public void startGettingChat(String chatID) {
        DomainControlFactory.getChatModelController().getMessages(chatID);
    }

    public void deactivatePolling() {
        DomainControlFactory.getChatModelController().deactivatePolling();
    }

    public String getUserEmail() {
        return LoginRespository.getUserEmail();//esto se debería buscar desde el modelo
    }

    public void getSubjects(String schooldID) {
        DomainControlFactory.getSubjectModelController().getSubjects(schooldID);
    }

    public void sendResponseOfSubjects(List<Subject> subjects) {
        PresentationControlFactory.getViewLayerController().sendResponseOfSubjects(subjects);
    }

    public void assignStudentToSubject(String subjectID, int activityIdentifier) {
        String userID = DomainControlFactory.getUserModelController().getUserId();
        DomainControlFactory.getSubjectModelController().assignUserToSubject(subjectID,userID,activityIdentifier);
    }

    public void notifyAssignStudent(boolean error) {
        PresentationControlFactory.getViewLayerController().notifyAssignStudent(error);
    }

    public void notifyListOfTeachersReceivedToAddSubject(List<User> contactsFromSelectedCenter) {
        PresentationControlFactory.getViewLayerController().notifyListOfTeachersReceivedToAddSubject(contactsFromSelectedCenter);
    }

    public void assignTeacherToSubject(String id, String subjectID, int activityIdentifier) {
        DomainControlFactory.getSubjectModelController().assignUserToSubject(subjectID,id,activityIdentifier);
    }

    public void notifyAssignedTeacher(boolean error) {
        PresentationControlFactory.getViewLayerController().notifyAssignedTeacher(error);
    }


    public void getGuests(String subjectID) {
        DomainControlFactory.getEventModelController().getGuests(subjectID);
    }

    public void sendResponseOfGuests(List<User> guests) {
        PresentationControlFactory.getViewLayerController().sendResponseOfGuests(guests);
    }

    public void setUserToken(String token) {
        DomainControlFactory.getUserModelController().setUserToken(token);
    }

    public void deleteUserToken(String token) {
        DomainControlFactory.getUserModelController().deleteUserToken(token);
    }

    public void createForumEntry(String titleEntry, String textEntry, String schoolId) {
        DomainControlFactory.getForumModelController().createForumEntry(titleEntry, textEntry, schoolId);
    }

    public void refreshLoggedUserSchoolsWallEntries() {
        DomainControlFactory.getForumModelController().refreshLoggedUserSchoolsWallEntries();
    }

    public void refreshLoggedUserSchoolsWallEntries(List<WallEntry> wallEntryList) {
        PresentationControlFactory.getViewLayerController().refreshLoggedUserSchoolsWallEntries(wallEntryList);
    }

    public School getSchoolById(String schoolId) {
       return DomainControlFactory.getSchoolsModelCrontroller().getSchoolById(schoolId);
    }


    public void getResults(String userID) {
        DomainControlFactory.getTracingTestResultModel().getTestResults(userID);
    }

    public void sendTestAnswers(List<TracingTest> results) {
        PresentationControlFactory.getViewLayerController().sendTestAnswers(results);
    }

    public void getClassSessions(String subjectID) {
        DomainControlFactory.getClassSessionsModelController().getClassSessions(subjectID);
    }

    public void getClassSessionsResult(boolean error, List<ClassSessionModel> classSessions) {
        PresentationControlFactory.getViewLayerController().getClassSessionsResult(error,classSessions);
    }

    public void getSubjectsFromUser(int activityIdentifier) {
        DomainControlFactory.getSubjectModelController().getSubjectsFromUser(activityIdentifier);
    }

    public void setSubjectsFromUserResult(boolean error, List<Subject> userSubjects) {
        PresentationControlFactory.getViewLayerController().setSubjectsFromUserResult(error,userSubjects);
    }

    public void createForumEntryReply(String wallEntryId, String content) {
        DomainControlFactory.getForumModelController().createForumEntryReply(wallEntryId, content, DomainControlFactory.getUserModelController().getLoggedUser().getId());
    }

    public void refreshWallEntryReplies(WallEntry wallEntry) {
        PresentationControlFactory.getViewLayerController().refreshWallEntryReplies(wallEntry);
    }

    public void createSubject(String subjectName, String schoolID) {
        DomainControlFactory.getSubjectModelController().createSubject(subjectName,schoolID);
    }

    public void setCreateSubjectResult(boolean error, int responseCode) {
        PresentationControlFactory.getViewLayerController().setCreateSubjectResult(error, responseCode);
    }


    public void setSchedule(String classID, List<Subject> l1, List<Subject> l2, List<Subject> l3, List<Subject> l4, List<Subject> l5) {
        DomainControlFactory.getScheduleModelController().setSchedule(classID,l1,l2,l3,l4,l5);
    }

    public void deleteWallEntry(String wallEntryId) {
        DomainControlFactory.getForumModelController().deleteWallEntry(wallEntryId);

    }

    public void getClassroomsOfSchool(String schoolID) {
        DomainControlFactory.getClassroomModelController().getClassroomsOfSchool(schoolID);
    }

    public void SetClassroomsBySchoolIDResponse(boolean error, List<Classroom> classroomsFromCurrentSchool) {
        PresentationControlFactory.getViewLayerController().SetClassroomsBySchoolIDResponse(error,classroomsFromCurrentSchool);
    }

    public void notifyListOfTeachersReceivedToCreateEvent(List<User> contactsFromSelectedCenter) {
        PresentationControlFactory.getViewLayerController().notifyListOfTeachersReceivedToCreateEvent(contactsFromSelectedCenter);
    }

    public void getSchedule(String classID) {
        DomainControlFactory.getScheduleModelController().getSchedule(classID);
    }

    public void sendResponseOfSchedule(List<Subject> l1, List<Subject> l2, List<Subject> l3, List<Subject> l4, List<Subject> l5) {
        PresentationControlFactory.getViewLayerController().sendResponseOfSchedule(l1,l2,l3,l4,l5);
    }

    public void toastMessage(int resourceMessage) {
        PresentationControlFactory.getViewLayerController().toastMessage(resourceMessage);
    }

    public void createEvent(ClassSessionModel classSession) {
        DomainControlFactory.getClassSessionsModelController().createEvent(classSession);
    }

    public void notifyCreateEventResponse(boolean error, int errorCode) {
        PresentationControlFactory.getViewLayerController().notifyCreateEventResponse(error,errorCode);
    }

    public void getTeachersBySubjectID(String subjectID) {
        DomainControlFactory.getUserModelController().getTeachersBySubjectID(subjectID);
    }

    public void setSendReservationRequestResponse(boolean error, int errorCode) {
        PresentationControlFactory.getViewLayerController().setSendReservationRequestResponse(error,errorCode);
    }

    public boolean isMySchool(String schoolID) {
        return DomainControlFactory.getUserModelController().isMySchool(schoolID);
    }

    public void sendError() {
        PresentationControlFactory.getViewLayerController().sendError();
    }

    public void startPollingChat(String chatID) {
        DomainControlFactory.getChatModelController().startPollingChat(chatID);
    }
    public void upgradeRole(String role) {
        DomainControlFactory.getUserModelController().upgradeRole(role);
    }
}