package com.example.gescov.viewlayer.ClassroomActivities.ClassroomDistribution;

import com.example.gescov.domainlayer.Classmodels.Assignment;

import java.util.List;

public class ClassroomDistributionInfo {
    private boolean error;
    private List<Assignment> students;

    ClassroomDistributionInfo(List<Assignment> students, Boolean error) {
        this.error = error;
        this.students = students;
    }

    public boolean isError() {
        return error;
    }

    public List<Assignment> getStudents() {
        return students;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
