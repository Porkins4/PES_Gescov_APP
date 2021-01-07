package com.example.gescov.domainlayer.Services.Volley.Interfaces;

import com.example.gescov.domainlayer.Classmodels.ClassSessionModel;

import org.json.JSONArray;

public interface IClassroomService {
    void getStudentsInClassRecord(String classroomId);

    void getClassroomInfo(String classroomID);

    void setSchedule(JSONArray classSchedule, String classID);

    void getClassroomsBySchoolID(String schoolID);

    void getSchedule(String classID);

    void createEvent(ClassSessionModel classSession);
}
