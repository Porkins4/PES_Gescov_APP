package com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassRecord;

import android.util.Pair;

import com.example.gescov.domainlayer.Classmodels.User;

import java.util.List;

public class StudentsInClassRecordResult {
    private List<Pair<User, Pair<Integer,Integer>>> studentNamesAndPos;
    private boolean error;

    public StudentsInClassRecordResult() {}

    StudentsInClassRecordResult(List<Pair<User, Pair<Integer,Integer>>> r, Boolean error) {
        this.error = error;
        this.studentNamesAndPos = r;
    }

    public Boolean getError() {
        return error;
    }

    public List<Pair<User, Pair<Integer,Integer>>> getStudentNamesAndPos() {
        return studentNamesAndPos;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
