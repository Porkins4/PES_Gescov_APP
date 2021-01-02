package com.example.gescov.domainlayer.Controllers;

import com.example.gescov.domainlayer.Classmodels.TracingTest;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.domainlayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TracingTestResultController {
    public void getTestResults(String userID) {
        ServicesFactory.getContagionService().getTestResults(userID);
    }

    public void sendTestAnswers(JSONArray response) {
        List<TracingTest> results = new ArrayList<>();
        for (int i = 0; i < response.length(); ++i ) {
            try {
                JSONObject element = response.getJSONObject(i);
                JSONArray answers = element.getJSONArray("answers");
                String date  = element.getString("dateTest");
                String testID = element.getString("id");
                String contID = element.getString("contID");
                List<Boolean> boolAnswers = new ArrayList<>();
                for ( int  j = 0; j < answers.length(); ++j) {
                    boolAnswers.add(answers.getBoolean(j));
                }
                TracingTest tracingTest = new TracingTest(testID, contID, boolAnswers,date);
                results.add(tracingTest);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        DomainControlFactory.getModelController().sendTestAnswers(results);
    }
}
