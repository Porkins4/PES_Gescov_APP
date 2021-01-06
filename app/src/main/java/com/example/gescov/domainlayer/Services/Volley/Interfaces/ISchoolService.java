
package com.example.gescov.domainlayer.Services.Volley.Interfaces;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolRequestResult;
import com.example.gescov.viewlayer.SignUpAndLogin.TokenVerificationResult;

import java.util.List;

public interface ISchoolService {

    String getDimensions(String schoolId, String classroomId);

    String getStudentsInClassroom(String classroom);

    String getAllSchools();

    void sendReservationRequest(String name, String classroom, int row, int col);

    void createSchoolRequest(String schoolName, String schoolAddress, String schoolState, String schoolWebsite, String latitude, String longitude, List<String> administratorsList, String creatorID);

    void createClassroomRequest(String schoolName, String classroomName, int classroomRows, int classroomCols);

    void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult, String classSession);

    void checkUserLogin(MutableLiveData<TokenVerificationResult> r);

    void refreshUser(String id);

    void addStudentToCenter(String id, String schoolId, MutableLiveData<SchoolRequestResult> result);

    void getSchool(String school);

    void getContactsFromCenter(String schoolID, int activityIdentifier);

    void getNumContagionPerSchool(int from);

    void setGraph(String schoolId);
}
