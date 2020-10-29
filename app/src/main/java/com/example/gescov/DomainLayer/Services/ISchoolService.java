package com.example.gescov.DomainLayer.Services;

public interface ISchoolService {

    String getDimensions(String schoolId, String classroomId);

    String getStudentsInClassroom(String classroom);

    String getAllSchools();

    void sendReservationRequest(String name, String classroom, int row, int col);
}
