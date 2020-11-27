package com.example.gescov.viewlayer.ClassroomActivities.ClassroomDistribution;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.DomainLayer.Classmodels.Assignment;
import com.example.gescov.DomainLayer.Classmodels.Classroom;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class ClassroomDsitributionViewModel extends ViewModel {
    private MutableLiveData<ClassroomDistributionInfo> data;
    private MutableLiveData<ClassroomDistributionClassInfo> classInfo;

    public LiveData<ClassroomDistributionInfo> getData(String classroomID,String date, String hour) {
        if (data == null) {
            data = new MutableLiveData<>();
            getAssignmentsInfo(classroomID,date,hour);
        }
        return data;
    }

    public void getAssignmentsInfo(String classroomID,String date, String hour) {
        PresentationControlFactory.getStudentsInClassSessionController().setClassroomDsitributionViewModel(this);
        PresentationControlFactory.getStudentsInClassSessionController().getAssignmentsForClassSession(classroomID,date,hour);
    }

    public void setAssignmentsResponse(List<Assignment> r, boolean b) {
        ClassroomDistributionInfo classroomDistributionInfo = new ClassroomDistributionInfo(r,b);
        data.setValue(classroomDistributionInfo);
    }

    public LiveData<ClassroomDistributionClassInfo> getClassroom(String classroomID) {
        if (classInfo == null) {
            classInfo = new MutableLiveData<>();
            getClassromInfo(classroomID);
        }
        return classInfo;
    }

    private void getClassromInfo(String classroomID) {
        PresentationControlFactory.getStudentsInClassSessionController().setClassroomDsitributionViewModel(this);
        PresentationControlFactory.getStudentsInClassSessionController().getClassroomInfo(classroomID);
    }

    public void setClassInfo(Classroom c, boolean b) {
        ClassroomDistributionClassInfo classroomDistributionClassInfo = new ClassroomDistributionClassInfo(c,b);
        classInfo.setValue(classroomDistributionClassInfo);
    }

    public Classroom getClassroomInfo() {
        return classInfo.getValue().getClassroom();
    }

    public List<Assignment> getAssignmentsList() {
        return data.getValue().getStudents();
    }
}