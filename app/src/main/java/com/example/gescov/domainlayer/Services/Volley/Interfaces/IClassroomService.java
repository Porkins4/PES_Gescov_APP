package com.example.gescov.domainlayer.Services.Volley.Interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IClassroomService {
    public void getStudentsInClassRecord(String classroomId, String date);

    void getClassroomInfo(String classroomID);

    void setSchedule(JSONArray classSchedule, String classID);
}
