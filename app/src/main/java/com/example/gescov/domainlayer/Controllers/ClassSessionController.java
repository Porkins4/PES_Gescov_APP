package com.example.gescov.domainlayer.Controllers;

import com.example.gescov.domainlayer.Classmodels.ClassSessionModel;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.domainlayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ClassSessionController {

    private List<ClassSessionModel> classSessions;

    public ClassSessionController() {
        classSessions = new ArrayList<>();
    }

    public void getClassSessions(String subjectID) {
        classSessions = new ArrayList<>();
        ServicesFactory.getSubjectsService().getClassSessions(subjectID);
    }

    public void setClassSessionsResult(boolean error, JSONArray response) {
        if (!error) {
            for (int i = 0; i < response.length(); ++i) {
                try {
                    ClassSessionModel classSession = ClassSessionModel.fromJSONtoClassSession(response.getJSONObject(i));
                    classSessions.add(classSession);
                } catch (JSONException e) {
                    error = true;
                }
            }
        }
        DomainControlFactory.getModelController().getClassSessionsResult(error, classSessions);
    }
}
