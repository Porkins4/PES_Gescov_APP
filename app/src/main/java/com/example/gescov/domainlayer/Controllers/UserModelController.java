package com.example.gescov.domainlayer.Controllers;

import android.location.Location;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.ISchoolService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.domainlayer.Singletons.LoginRespository;
import com.example.gescov.domainlayer.Singletons.ServicesFactory;
import com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolRequestResult;
import com.example.gescov.viewlayer.SignUpAndLogin.TokenVerificationResult;
import com.example.gescov.viewlayer.home.ContagionRequestResult;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserModelController {
    private User loggedUser;
    private HashMap<String, User> userHash;
    private List<User> contactsFromSelectedCenter;

    public UserModelController() {
        loggedUser = new User();
        userHash = new HashMap<>();
    }

    public String getStudentsInClassroom(String classroom) {
        return loggedUser.getStudentsInClassroom(classroom);
    }

    public void initUser() {
        loggedUser = new User();
    }


    public String getContagionsOfMyCenter(String schoolID) {
        return loggedUser.getCntagionsOfCenter(schoolID);
    }


    //stub at the moment
    public boolean containsSchool(String schoolId) {
        return true;
    }

    //el colegio es contenido por el usuario
    public String getClassroomDimensions(String schoolId, String classroomId) {
        return loggedUser.getClassroomDimensions(schoolId, classroomId);
    }

    public String getAllSchools() {
        return loggedUser.getAllSchools();
    }

    public void refreshSchoolList() {
        loggedUser.refreshSchoolList();
    }

    public void notifyInfected(MutableLiveData<ContagionRequestResult> result) {
        // el comportamineto seria obtener la escuela de nuestro usuario y pasarsela
        loggedUser.notifiyContagion(result);

    }

    public void sendReservationRequest(String aula, int row, int col) {
        loggedUser.sendReservationRequest(aula,row,col);
    }

    public void createSchool(String schoolName, String schoolAddress, String schoolTelephone, String schoolWebsite, String latitude, String longitude) {

        ISchoolService schoolService = ServicesFactory.getSchoolService();
        List<String> administratorsList = new ArrayList<>();
        administratorsList.add(loggedUser.getId());
        schoolService.createSchoolRequest(schoolName, schoolAddress, schoolTelephone, schoolWebsite, latitude, longitude, administratorsList, loggedUser.getId());
    }

    public void deleteSchool(String schoolId) {
        loggedUser.deleteSchool(schoolId);
    }

    public void refreshSchoolClassrooms(String schoolName) {
        loggedUser.getSchoolClassrooms(schoolName);
    }


    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult, String classSession) {
        ISchoolService iSchoolService = ServicesFactory.getSchoolService();
        iSchoolService.getStudentsInClassSession(studentsResult, classSession);
    }

    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result) {
        loggedUser.notifyRecovery(result);
    }

    public void sendAnswers(List<Boolean> answers) {
        loggedUser.sendAnswers(answers);
    }
  
    public void checkLoginUser(MutableLiveData<TokenVerificationResult> r) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        schoolService.checkUserLogin(r);
    }

    public void updateUserId(String userId) {
        loggedUser.setId(userId);
    }

    public String getUserId() {
        return loggedUser.getId();
    }

    public void updateUserName(String userName) {
        loggedUser.setName(userName);
    }

    public void updateClassroom(String classroomId, String classroomName, int numRows, int numCols) {
        loggedUser.updateSchoolClassroom(classroomId, classroomName, numRows, numCols);

    }

    public void deleteClassroom(String classroomId) {
        loggedUser.deleteSchoolClassroom(classroomId);
    }

    public void refreshLoggedUser(JSONObject response) {
        loggedUser = getUserFromJSONObject(response);
        //DomainControlFactory.getModelController().updateHomeViewModel(loggedUser.getName());
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void refreshUser(String id) {
        User user = getUserById(id);
        user.refresh();
    }

    public User getUserById(String id) {
        User user = userHash.get(id);
        if (user == null) {
            user = new User();
        }
        userHash.put(id, user);
        return user;
    }

    public void refreshUser(String id, JSONObject response) {
        if (userHash.containsKey(id)){
            userHash.remove(id);
        }
        User user = getUserFromJSONObject(response);
        userHash.put(user.getId(), user);
        //DomainControlFactory.getModelController().updateHomeViewModel(loggedUser.getName());
    }

    public void addStudentToCenter(String schoolId, MutableLiveData<SchoolRequestResult> result) {
        ServicesFactory.getCreateRequestToSchoolResponseController().createRequestToSchool(loggedUser.getId(), schoolId);
    }


    public void updateLoggedUserRisk() {
        loggedUser.updateRisk();
    }

    public void changeUserProfile(boolean isStudent) {
        loggedUser.changeUserProfile(isStudent);
    }

    public void setUserType(String profile) {
        loggedUser.setProfileType(profile);
    }

    public User.UserProfileType getProfileType() {
        return loggedUser.getProfileType();
    }

    public void setNewSchoolID(String schoolId) {
        loggedUser.setNewSchoolID(schoolId);
    }

    public void notifyPossibleContagion(MutableLiveData<ContagionRequestResult> result) {
        loggedUser.notifyPossibleContagion(result);
    }


    //-----------------------------------------------
    //login operations
    public void setUserLoggedIn(String serverClientID) {
        LoginRespository.checkuserAlreadyLoggedInOnthisDevice(serverClientID);
    }

    public void getUserID() {
        ServicesFactory.getUserService().getUserID(LoginRespository.getToken());
    }

    public void setUserID(Boolean error, String userID) {
        if (!error) {
            loggedUser.setId(userID);
        }
        DomainControlFactory.getModelController().setUserIDVerificationResult(error);
    }

    public void refreshLoggedUser() {
        ServicesFactory.getUserService().getUserInfo(loggedUser.getId());
    }

    public void setLoggedInUser(Boolean error, JSONObject response) {
        if (!error){
            loggedUser = getUserFromJSONObject(response);
            updateContagionId();
        }
    }

    public User getUserFromJSONObject(JSONObject response) {
        User user = new User();
        JSONArray userArray = null;
        try {
            userArray = response.getJSONArray("schoolsID");
            List<String> schoolsList = new ArrayList<>();
            for (int i = 0; i < userArray.length(); ++i) {
                schoolsList.add(userArray.getString(i));
            }
            String id = response.getString("id");
            String name = response.getString("name");
            String email = response.getString("email");
            String pic = response.getString("pic");
            boolean isStudent = response.getBoolean("student");
            String tokenId = response.getString("id");
            user = new User(name, id, schoolsList, isStudent, email, tokenId, pic);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getLoggedInUser() {
        return loggedUser;
    }

    public void updateContagionId() {
        ServicesFactory.getContagionService().getContagionID(loggedUser.getId());
    }

    public void setContagionId(String contagionId, Boolean error) {
        loggedUser.setIdContagion(contagionId);
        DomainControlFactory.getSubjectModelController().getSubjectsFromUser(2);
    }

    public void updateContagionID(String contagionId) {
        loggedUser.setIdContagion(contagionId);
    }

    public GoogleSignInClient getGoogleSignInClient(String serverClientID) {
        return LoginRespository.getGoogleSignInClient(serverClientID);
    }

    //-----------------------------------------------------------------
    //update user schools
    public void updateSchools() {
        DomainControlFactory.getSchoolsModelCrontroller().updateSchools(loggedUser.getSchoolsID());
    }

    public void setContactsFromSelectedCenter(JSONArray response, int activityIdentifier) {
        contactsFromSelectedCenter = new ArrayList<>();
        for (int i = 0; i < response.length(); ++i) {
            try {
                User u = getUserFromJSONObject(response.getJSONObject(i)); //reusar esta operación
                addUserToContactList(u,activityIdentifier);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (activityIdentifier == 1) DomainControlFactory.getModelController().updateContactsFromCreateChat();
        else DomainControlFactory.getModelController().notifyListOfTeachersReceivedToAddSubject(contactsFromSelectedCenter);
    }

    private void addUserToContactList(User u, int activityIdentifier) {
        if (loggedUser.getId().equals(u.getId())) return;
        if (activityIdentifier == 1) {
            if (loggedUser.getProfileType() == User.UserProfileType.STUDENT && u.getProfileType() == User.UserProfileType.TEACHER)
                contactsFromSelectedCenter.add(u);
            else if (loggedUser.getProfileType() == User.UserProfileType.TEACHER)
                contactsFromSelectedCenter.add(u);
        } else {
            if (u.getProfileType() == User.UserProfileType.TEACHER)
                contactsFromSelectedCenter.add(u);
        }
    }

    public List<User> getContacts() {
        return contactsFromSelectedCenter;
    }

    public void setUsersList(String usersListResponse) {
        JSONArray response = null;

        userHash = new HashMap<>();
        try {
            response = new JSONArray(usersListResponse);
            for (int i = 0; i < response.length(); ++i) {

                JSONObject aux = response.getJSONObject(i);
                User user = getUserFromJSONObject(aux);
                userHash.put(user.getId(), user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DomainControlFactory.getModelController().refreshSchoolUsersListInView(new ArrayList <User> (userHash.values()));
    }

    public void setLocation(Location location) {
        loggedUser.setLocation(location);
    }

    public Location getLocation() {
       return  loggedUser.getLocation();
    }

    public String getIdContagion() {
        return loggedUser.getIdContagion();
    }

    public void setSubjectID(String subjectID, boolean error, int activityIdentifier) {
        if (! error ) {
            DomainControlFactory.getSubjectModelController().getSubjectsFromUser(-1);
            loggedUser.addSubjectID(subjectID);
        }
        if (activityIdentifier == 1) DomainControlFactory.getModelController().notifyAssignStudent(error);
        else DomainControlFactory.getModelController().notifyAssignedTeacher(error);
    }

    public void setUserToken(String token) {
        ServicesFactory.getUserService().setUserToken(loggedUser.getId(),token);
    }

    public void deleteUserToken(String token) {
        ServicesFactory.getUserService().deleteUserToken(loggedUser.getId(),token);
    }

    public void getTeachersBySubjectID(String subjectID) {
        ServicesFactory.getSubjectsService().getUsersBySubjectID(subjectID);
    }

    public void setUsersBySubjectIDResult(JSONArray response) {
        contactsFromSelectedCenter = new ArrayList<>();
        for (int i = 0; i < response.length(); ++i) {
            try {
                contactsFromSelectedCenter.add(getUserFromJSONObject(response.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        DomainControlFactory.getModelController().notifyListOfTeachersReceivedToCreateEvent(contactsFromSelectedCenter);
    }

    public void setSendReservationRequestResponse(boolean error, int errorCode, JSONObject response) {
        DomainControlFactory.getModelController().setSendReservationRequestResponse(error,errorCode);
    }

    public boolean isMySchool(String schoolID) {
        return loggedUser.isMySchool(schoolID);
    }

    public void upgradeRole(String role) {
        ServicesFactory.getUpgradeUserRoleResponseController().upgradeUserRole(loggedUser.getId(), role);
    }
}
