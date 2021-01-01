package com.example.gescov.domainlayer.Controllers;

import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.domainlayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubjectModelController {
    public void getSubjects(String schooldID) {
        ServicesFactory.getSubjectsService().getSubjects(schooldID);
    }

    public void sendResponseOfSubjects(JSONArray response) {
        List<Subject> subjects = new ArrayList<>();
        for ( int i = 0; i < response.length(); ++i) {
            try {
                JSONObject subjectJson = response.getJSONObject(i);
                JSONArray students = subjectJson.getJSONArray("studentsID");
                List<String> studentsID = new ArrayList<>();
               for (int j  = 0; j < students.length(); ++j) {
                   String studentID = students.getString(j);
                   studentsID.add(studentID);
               }
               JSONArray teachers = subjectJson.getJSONArray("teachersID");
                List<String> teachersID = new ArrayList<>();
               for (int j = 0; j < teachers.length(); ++j) {
                   String teacherID = teachers.getString(j);
                   teachersID.add(teacherID);
               }
               Subject subject = new Subject(subjectJson.getString("id"),subjectJson.getString("name"), subjectJson.getString("schoolID"),studentsID,teachersID);
               subjects.add(subject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        DomainControlFactory.getModelController().sendResponseOfSubjects(subjects);

    }

    public void assignUserToSubject(String subjectID, String userID, int activityIdentifier) {
        ServicesFactory.getSubjectsService().assignUserToSubject(subjectID,userID,activityIdentifier);
    }
}
