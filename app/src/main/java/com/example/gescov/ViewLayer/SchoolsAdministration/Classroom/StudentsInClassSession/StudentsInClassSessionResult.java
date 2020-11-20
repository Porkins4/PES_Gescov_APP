package com.example.gescov.ViewLayer.SchoolsAdministration.Classroom.StudentsInClassSession;

import java.util.List;

public class StudentsInClassSessionResult {
    private Boolean error;
    private List<String> studentNames;

    public StudentsInClassSessionResult() {

    }

    StudentsInClassSessionResult(Boolean error, List<String> studentNames) {
        this.error = error;
        this.studentNames = studentNames;
    }

    public Boolean getError() {
        return error;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public void setStudentNames(List<String> studentNames) {
        this.studentNames = studentNames;
    }
}
