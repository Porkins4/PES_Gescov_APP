package com.example.gescov.domainlayer.Classmodels;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String id;
    private String name;
    private List<String> teachers;
    private String schoolID;
    private List<String> students;
    private String schoolName;

    public Subject(String id, String name,  String schoolID, List<String> students, List<String> teachers) {
        this.id = id;
        this.name = name;
        this.teachers = teachers;
        this.schoolID = schoolID;
        this.students = students;
    }

    public static Subject fromJSONtoSubject(JSONObject response) {
        try {
            String id = response.getString("id");
            String name = response.getString("name");
            String schoolID = response.getString("schoolID");
            List<String> studentsID = new ArrayList<>();
            JSONArray students = response.getJSONArray("studentsID");
            for (int i  = 0; i < students.length(); ++i) {
                String studentID = students.getString(i);
                studentsID.add(studentID);
            }
            List<String> teachersID = new ArrayList<>();
            JSONArray teachers = response.getJSONArray("teachersID");
            for (int i  = 0; i < teachers.length(); ++i) {
                String teacherID = teachers.getString(i);
                teachersID.add(teacherID);
            }
            return new Subject(id,name,schoolID,studentsID,teachersID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<String> teachers) {
        this.teachers = teachers;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }
}
