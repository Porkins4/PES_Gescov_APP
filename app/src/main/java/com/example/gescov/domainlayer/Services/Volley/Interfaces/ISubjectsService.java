package com.example.gescov.domainlayer.Services.Volley.Interfaces;

public interface ISubjectsService {
    void getSubjects(String schooldID);

    void assignUserToSubject(String subjectID, String userID, int activityIdentifier);
}
