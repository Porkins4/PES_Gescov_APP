package com.example.gescov.domainlayer.Classmodels;

import com.google.android.gms.common.util.JsonUtils;

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

    private String classroomName;
    private String teacherName;

    public ClassSessionModel() {}

    public ClassSessionModel(String id, String hour, String finishHour, String date, String classroomID, String subjectID, String teacherID, String concept, String classroomName, String teacherName) {
        this.id = id;
        this.hour = hour;
        this.finishHour = finishHour;
        this.date = date;
        this.classroomID = classroomID;
        this.subjectID = subjectID;
        this.teacherID = teacherID;
        this.concept = concept;
        this.classroomName = classroomName;
        this.teacherName = teacherName;
    }

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
            JSONObject first = response.getJSONObject("first");
            JSONObject second = response.getJSONObject("second");
            System.out.println(response);
            String id = first.getString("id");
            String hour = first.getString("hour");
            String finishHour = first.getString("finishHour");
            String date = first.getString("date");
            String classroomID = first.getString("classroomID");
            String subjectID = first.getString("subjectID");
            String teacherID = first.getString("teacherID");
            String concept = first.getString("concept");
            String classroomName = second.getString("first");
            String teacherName = second.getString("second");
            return new ClassSessionModel(id, hour, finishHour, date, classroomID, subjectID, teacherID, concept,classroomName,teacherName);
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

    public String getSubjectID() {
        return subjectID;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void print() {
        System.out.println("class ID: " + id);
        System.out.println(hour);
        System.out.println(finishHour);
        System.out.println(date);
        System.out.println("class ID: " + classroomID);
        System.out.println("subject ID: " + subjectID);
        System.out.println("teacher ID: " + teacherID);
        System.out.println(concept);
    }
}
