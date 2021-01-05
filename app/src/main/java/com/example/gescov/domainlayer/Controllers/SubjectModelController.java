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

    private List<Subject> userSubjects;

    public SubjectModelController() {
        userSubjects = new ArrayList<>();
    }

    public void getSubjects(String schooldID) {
        ServicesFactory.getSubjectsService().getSubjects(schooldID);
    }

    public void sendResponseOfSubjects(JSONArray response) {
        List<Subject> subjects = new ArrayList<>();
        for ( int i = 0; i < response.length(); ++i) {
            try {
               Subject subject = Subject.fromJSONtoSubject(response.getJSONObject(i));
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

    public void getSubjectsFromUser() {
        ServicesFactory.getSubjectsService().getSubjectsFromUser(DomainControlFactory.getUserModelController().getUserId());
    }

    public void setSubjectsFromUserResult(boolean error, JSONArray response) {
        userSubjects = new ArrayList<>();
        if (!error) {
            try {
                for (int i = 0; i < response.length(); ++i) {
                    Subject s = Subject.fromJSONtoSubject(response.getJSONObject(i));
                    userSubjects.add(s);
                }
            } catch (JSONException e) {
                error = true;
            }
        }
        DomainControlFactory.getModelController().setSubjectsFromUserResult(error,userSubjects);
    }

    public void createSubject(String subjectName, String schoolID) {
        ServicesFactory.getSubjectsService().createSubject(subjectName, schoolID, DomainControlFactory.getUserModelController().getUserId());
    }

    public void setCreateSubjectResult(boolean error, JSONObject response) {
        DomainControlFactory.getModelController().setCreateSubjectResult(error);
    }
}
