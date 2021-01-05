package com.example.gescov.domainlayer.Classmodels;

import org.json.JSONException;
import org.json.JSONObject;

public class ClassSessionModel {

    private String id;
    private String hour;
    private String finishHour;
    private String date;
    private String classroomID;
    private String subjectID;
    private String teacherID;
    private String concept;

    public ClassSessionModel() {}

    public ClassSessionModel(String id, String hour, String finishHour, String date, String classroomID, String subjectID, String teacherID, String concept) {
        this.id = id;
        this.hour = hour;
        this.finishHour = finishHour;
        this.date = date;
        this.classroomID = classroomID;
        this.subjectID = subjectID;
        this.teacherID = teacherID;
        this.concept = concept;
    }

    public static ClassSessionModel fromJSONtoClassSession(JSONObject response) {
        try {
            String id = response.getString("id");
            String hour = response.getString("hour");
            String finishHour = response.getString("finishHour");
            String date = response.getString("date");
            String classroomID = response.getString("classroomID");
            String subjectID = response.getString("subjectID");
            String teacherID = response.getString("teacherID");
            String concept = response.getString("concept");
            return new ClassSessionModel(id, hour, finishHour, date, classroomID, subjectID, teacherID, concept);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public String getHour() {
        return hour;
    }

    public String getFinishHour() {
        return finishHour;
    }

    public String getDate() {
        return date;
    }

    public String getClassroomID() {
        return classroomID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getConcept() {
        return concept;
    }
}
