package com.example.gescov.domainlayer.Classmodels;

import java.util.List;

public class TracingTest {
    String id;
    String contID;
    List<Boolean> answers;
    String date;

    public TracingTest(String id, String contID, List<Boolean> answers, String date) {
        this.id = id;
        this.contID = contID;
        this.answers = answers;
        this.date = date;
    }


    public String getContID() {
        return contID;
    }

    public List<Boolean> getAnswers() {
        return answers;
    }

    public String getDate() {
        return date;
    }
}
