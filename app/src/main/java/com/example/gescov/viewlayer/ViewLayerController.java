package com.example.gescov.viewlayer;

import android.location.Location;
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


public class ViewLayerController {

    public ViewLayerController() {
    }

    public String getStudentsInClassroom(String classroom) {
        return DomainControlFactory.getModelController().getStudentsInClassroom(classroom);
    }

    public String getAllContagions(String schoolID) {
        return DomainControlFactory.getModelController().getAllContagions(schoolID);
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

    public void createSchool( String schoolName, String schoolAddress, String schoolTelephone, String schoolWebsite, String latitude, String longitude) {
        DomainControlFactory.getModelController().createSchool(schoolName, schoolAddress, schoolTelephone, schoolWebsite, latitude, longitude);
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
        } catch (JSONException  | AdapterNotSetException e) {
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

    public void addStudentToCenter(String schoolId, MutableLiveData<SchoolRequestResult> result) {
        DomainControlFactory.getModelController().addStudentToCenter(schoolId,result);
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

    public void getAssignmentsForClassSession(String classSessionID) {
        DomainControlFactory.getModelController().getAssignmentsForClassSession(classSessionID);
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

    public void refreshLoggedUser() {
        DomainControlFactory.getModelController().refreshLoggedUser();
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

    //ActivityIdentifier indicates which activity has to be warn that the result has been received
    // activityIdentifier = 1 => CreateChatActivity
    // activityIdentifier = 2 => AddTeacherToSubject
    public void getContactsFromCenter(String schoolID, int activityIdentifier) {
        DomainControlFactory.getModelController().getContactsFromCenter(schoolID,activityIdentifier);
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


    public void setLocation(Location location) {
        DomainControlFactory.getUserModelController().setLocation(location);
    }

    public Location getLocation() {
        return DomainControlFactory.getUserModelController().getLocation();
    }

    public void setGraph(String schoolId) {
        DomainControlFactory.getModelController().setGraph(schoolId);
    }

    public void sendResponseOfGraph(List<Pair<String, Integer>> contagionPerMonth) {
        PresentationControlFactory.getSchoolsCrontroller().sendResponseOfGraph(contagionPerMonth);
    }

    //----------------------------------
    //Chats
    public void createChat(String targetID) {
        DomainControlFactory.getModelController().createChat(targetID);
    }

    public void chatCreatedInBack(Chat chat, boolean error) {
        PresentationControlFactory.getCreateChatController().chatCreatedInBack(chat, error);
    }
  
    public void updateChatPreview() {
        DomainControlFactory.getModelController().updateChatPreview();
    }

    public void chatPreviewsUpdated(List<ChatPreviewModel> chatPreviewModels, boolean error) {
        PresentationControlFactory.getChatListController().chatPreviewsUpdated(chatPreviewModels, error);
    }

    public void requestAcessSchoolByCode(String userId, String schoolId, String schoolCode) {
       DomainControlFactory.getSchoolsModelCrontroller().requestAccessSchoolByCode(userId, schoolId, schoolCode);
    }

    public void getChatMessages(String chatID) {
        DomainControlFactory.getModelController().getChatMessages(chatID);
    }

    public void notifyChatMessagesResponse(List<MessageModel> messages, boolean error) {
        PresentationControlFactory.getChatViewController().notifyChatMessagesResponse(messages, error);
    }

    public void sendMessage(String chatID, String message) {
        DomainControlFactory.getModelController().sendMessage(chatID,message);
    }

    public void notifyChatUpdated() {
        PresentationControlFactory.getChatViewController().notifyChatUpdated();
    }

    public void deleteSchoolAdmin(String adminID) {
        DomainControlFactory.getModelController().deleteSchoolAdmin(adminID);
    }

    public void currentSchoolRefreshed() {
        PresentationControlFactory.getSchoolUsersController().currentSchoolRefreshed();
    }

    public School getCurrentSchool() {
        return DomainControlFactory.getModelController().getCurrentSchool();
    }

    public String getIdContagion() {
        return DomainControlFactory.getModelController().getIdContagion();
    }

    public void updateCoordinatesSchoolCreationForm(String latitude, String longitude) {
        PresentationControlFactory.getSchoolsCrontroller().updateCoordinatesSchoolCreationForm(latitude, longitude);
    }

    public void startGettingChat(String chatID) {
        DomainControlFactory.getModelController().startGettingChat(chatID);
    }

    public void deactivatePolling() {
        DomainControlFactory.getModelController().deactivatePolling();
    }

    public String getUserEmail() {
        return DomainControlFactory.getModelController().getUserEmail();
    }

    public void getSubjects(String schooldID) {
        DomainControlFactory.getModelController().getSubjects(schooldID);
    }

    public void sendResponseOfSubjects(List<Subject> subjects) {
        PresentationControlFactory.getSubjectController().sendResponseOfSubjects(subjects);
    }

    //ActivityIdentifier indicates which activity has to be warn that the result has been received
    // activityIdentifier = 1 => SubjectDetailsActivity
    // activityIdentifier = 2 => AddTeacherToSubject
    public void assignStudentToSubject(String subjectID, int activityIdentifier) {
        DomainControlFactory.getModelController().assignStudentToSubject(subjectID,activityIdentifier);
    }

    public void notifyAssignStudent(boolean error) {
        PresentationControlFactory.getSubjectController().notifyAssignStudent(error);
    }

    public void notifyListOfTeachersReceivedToAddSubject(List<User> contactsFromSelectedCenter) {
        PresentationControlFactory.getSubjectController().notifyListOfTeachersReceivedToAddSubject(contactsFromSelectedCenter);

    }

    public void assignTeacherToSubject(String id, String subjectID, int activityIdentifier) {
        DomainControlFactory.getModelController().assignTeacherToSubject(id,subjectID,activityIdentifier);
    }

    public void notifyAssignedTeacher(boolean error) {
        PresentationControlFactory.getSubjectController().notifyAssignedTeacher(error);
    }


    public void getGuests(String subjectID) {
        DomainControlFactory.getModelController().getGuests(subjectID);
    }

    public void sendResponseOfGuests(List<User> guests) {
        PresentationControlFactory.getEventController().sendResponseOfGuests(guests);
    }

    public void setUserToken(String token) {
        DomainControlFactory.getModelController().setUserToken(token);
    }

    public void deleteUserToken(String token) {
        DomainControlFactory.getModelController().deleteUserToken(token);
    }

    public void createForumEntry(String titleEntry, String textEntry, String schoolId) {
        DomainControlFactory.getModelController().createForumEntry(titleEntry, textEntry, schoolId);
    }

    public void refreshLoggedUserSchoolsWallEntries() {
        DomainControlFactory.getModelController().refreshLoggedUserSchoolsWallEntries();
    }

    public void refreshLoggedUserSchoolsWallEntries(List<WallEntry> wallEntryList) {
        PresentationControlFactory.getForumController().refreshList(wallEntryList);
    }

    public School getSchoolById(String schoolId) {
        return DomainControlFactory.getModelController().getSchoolById(schoolId);
    }


    public void getResults(String userID) {
        DomainControlFactory.getModelController().getResults(userID);
    }

    public void sendTestAnswers(List<TracingTest> results) {
        PresentationControlFactory.getTracingTestController().sendTestAnswers(results);
    }

    public void getClassSessions(String subjectID) {
        DomainControlFactory.getModelController().getClassSessions(subjectID);
    }

    public void getClassSessionsResult(boolean error, List<ClassSessionModel> classSessions) {
        PresentationControlFactory.getSubjectController().getClassSessionsResult(error,classSessions);

    }

    public void getSubjectsFromUser() {
        DomainControlFactory.getModelController().getSubjectsFromUser();
    }

    public void setSubjectsFromUserResult(boolean error, List<Subject> userSubjects) {
        PresentationControlFactory.getSubjectController().setSubjectsFromUserResult(error,userSubjects);

    }

    public void createForumEntryReply(String wallEntryId, String content) {
        DomainControlFactory.getModelController().createForumEntryReply(wallEntryId, content);
    }

    public void refreshWallEntryReplies(WallEntry wallEntry) {
        PresentationControlFactory.getForumRepliesController().refreshWallEntryReplies(wallEntry);
    }

    public void createSubject(String subjectName, String schoolID) {
        DomainControlFactory.getModelController().createSubject(subjectName, schoolID);
    }

    public void setCreateSubjectResult(boolean error) {
        PresentationControlFactory.getSubjectController().setCreateSubjectResult(error);
    }

    public void getClassroomsOfSchool(String schoolID) {
        DomainControlFactory.getModelController().getClassroomsOfSchool(schoolID);
    }

    public void SetClassroomsBySchoolIDResponse(boolean error, List<Classroom> classroomsFromCurrentSchool) {
        PresentationControlFactory.getEventController().SetClassroomsBySchoolIDResponse(error,classroomsFromCurrentSchool);
    }

    public void notifyListOfTeachersReceivedToCreateEvent(List<User> contactsFromSelectedCenter) {
        PresentationControlFactory.getEventController().notifyListOfTeachersReceivedToCreateEvent(contactsFromSelectedCenter);
    }


    public void setSchedule(String classID, List<Subject> l1, List<Subject> l2, List<Subject> l3, List<Subject> l4, List<Subject> l5) {
        DomainControlFactory.getModelController().setSchedule(classID,l1,l2,l3,l4,l5);
    }

    public void deleteWallEntry(String wallEntryId) {
        DomainControlFactory.getModelController().deleteWallEntry(wallEntryId);
    }

    public void toastMessage(int resourceMessage) {
        PresentationControlFactory.getMessagesManager().toastMessage(resourceMessage);
    }

    public void getSchedule(String classID) {
        DomainControlFactory.getModelController().getSchedule(classID);
    }

    public void sendResponseOfSchedule(List<Subject> l1, List<Subject> l2, List<Subject> l3, List<Subject> l4, List<Subject> l5) {
        PresentationControlFactory.getScheduleController().sendResponseOfSchedule(l1,l2,l3,l4,l5);
    }

    public void createEvent(ClassSessionModel classSession) {
        DomainControlFactory.getModelController().createEvent(classSession);
    }

    public void notifyCreateEventResponse(boolean error, int errorCode) {
        PresentationControlFactory.getEventController().notifyCreateEventResponse(error,errorCode);
    }

    public void getTeachersBySubjectID(String subjectID) {
        DomainControlFactory.getModelController().getTeachersBySubjectID(subjectID);
    }

    public boolean isMySchool(String schoolID) {
        return DomainControlFactory.getModelController().isMySchool(schoolID);
    }

}

