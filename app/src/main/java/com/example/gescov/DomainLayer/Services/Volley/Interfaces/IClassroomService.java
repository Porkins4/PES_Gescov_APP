package com.example.gescov.DomainLayer.Services.Volley.Interfaces;

public interface IClassroomService {
    public void getStudentsInClassRecord(String classroomId, String date);

    void getClassroomInfo(String classroomID);
}
