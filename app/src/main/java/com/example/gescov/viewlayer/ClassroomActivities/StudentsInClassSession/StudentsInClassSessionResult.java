package com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession;

import com.example.gescov.domainlayer.Classmodels.Assignment;

import java.util.List;

public class StudentsInClassSessionResult {
    private Boolean error;
    private List<Assignment> studentNames;

    public StudentsInClassSessionResult() {

    }

    StudentsInClassSessionResult(Boolean error, List<Assignment> studentNames) {
        this.error = error;
        this.studentNames = studentNames;
    }

    public Boolean getError() {
        return error;
    }

    public List<Assignment> getStudentNames() {
        return studentNames;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public void setStudentNames(List<Assignment> studentNames) {
        this.studentNames = studentNames;
    }
}
