package com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassRecord;

import android.util.Pair;

import java.io.StringReader;
import java.util.List;

public class StudentsInClassRecordResult {
    private List<Pair<String,String>> studentNamesAndPos;
    private boolean error;

    public StudentsInClassRecordResult() {}

    StudentsInClassRecordResult(List<Pair<String,String>> r, Boolean error) {
        this.error = error;
        this.studentNamesAndPos = r;
    }

    public Boolean getError() {
        return error;
    }

    public List<Pair<String, String>> getStudentNamesAndPos() {
        return studentNamesAndPos;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
