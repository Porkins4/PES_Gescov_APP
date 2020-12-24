package com.example.gescov.domainlayer.Services.Volley.Interfaces;

public interface ISubjectsService {
    void getSubjects(String schooldID);

    void assignStudent(String subjectID, String userID);
}
