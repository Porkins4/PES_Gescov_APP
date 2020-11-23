package com.example.gescov.ViewLayer.ClassroomActivities.ClassroomDistribution;

import com.example.gescov.DomainLayer.Classmodels.Assignment;
import com.example.gescov.DomainLayer.Classmodels.Classroom;

import java.util.List;

public class ClassroomDistributionClassInfo {
    private boolean error;
    private Classroom classroom;

    ClassroomDistributionClassInfo() {}

    ClassroomDistributionClassInfo(Classroom classroom, Boolean error) {
        this.error = error;
        this.classroom = classroom;
    }

    public boolean isError() {
        return error;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public int getClassroomRows(){
        return classroom.getRows();
    }

    public int getClassroomCols(){
        return classroom.getColumns();
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
