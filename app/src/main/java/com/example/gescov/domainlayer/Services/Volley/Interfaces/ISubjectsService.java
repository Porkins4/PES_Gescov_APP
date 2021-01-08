package com.example.gescov.domainlayer.Services.Volley.Interfaces;

public interface ISubjectsService {
    void getSubjects(String schooldID);

    void assignUserToSubject(String subjectID, String userID, int activityIdentifier);

    void getClassSessions(String subjectID);

    void getSubjectsFromUser(String userID, int activityIdentifier);

    void createSubject(String subjectName, String schoolID, String userId);

    void getUsersBySubjectID(String subjectID);
}
