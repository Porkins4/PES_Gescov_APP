package com.example.gescov.DomainLayer.Services;

import java.util.List;

public interface ISchoolService {

    String getDimensions(String schoolId, String classroomId);

    String getStudentsInClassroom(String classroom);

    String getAllSchools();

    void sendReservationRequest(String name, String classroom, int row, int col);

    void createSchoolRequest(String schoolName, String schoolAddress, String schoolState, String schoolWebsite, List<String> administratorsList, String creatorID);

    void createClassroomRequest(String schoolName, String schoolAddress, String schoolState, float schoolLatitude, float schoolLongitude, String schoolCreator, String classroomName, String classrooomCapacity, String classroomRows, String classroomCols);

    void deleteSchoolRequest(String schoolID, String userID);

    String getSchoolClassrooms(String schoolId, String userName);
}
