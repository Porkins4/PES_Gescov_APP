package com.example.gescov.domainlayer.Services.Volley.Interfaces;

import org.json.JSONObject;

public interface IClassroomService {
    public void getStudentsInClassRecord(String classroomId, String date);

    void getClassroomInfo(String classroomID);

    void setSchedule(JSONObject classSchedule,String classID);
}
