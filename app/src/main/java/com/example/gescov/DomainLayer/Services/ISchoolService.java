package com.example.gescov.DomainLayer.Services;

public interface ISchoolService {

    String getDimensions(String schoolId, String classroomId);

    String getStudentsInClassroom(String classroom);

    String getAllSchools();

    void sendReservationRequest(String name, String classroom, int row, int col);

    void createSchoolRequest(String schoolName, String schoolAddress, String creator);

    void createClassroomRequest(String schoolName, String schoolAddress, String schoolState, float schoolLatitude, float schoolLongitude, String schoolCreator, String classroomName, String classrooomCapacity, String classroomRows, String classroomCols);
}
