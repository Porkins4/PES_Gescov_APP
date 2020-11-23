package com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassSession;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.Classmodels.Assignment;
import com.example.gescov.DomainLayer.Classmodels.Classroom;
import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.ViewLayer.ClassroomActivities.ClassroomDistribution.ClassroomDsitributionViewModel;
import com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassRecord.StudentsInClassRecordViewModel;
import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;

import java.util.List;

public class StudentsInClassSessionController {

    private StudentsInClassRecordViewModel studentsInClassRecordViewModel; //hacerlo también para el otro view
    private ClassroomDsitributionViewModel classroomDsitributionViewModel;

    public void setStudentsInClassRecordViewModel(StudentsInClassRecordViewModel studentsInClassRecordActivity) {
        this.studentsInClassRecordViewModel = studentsInClassRecordActivity;
    }

    public void setClassroomDsitributionViewModel(ClassroomDsitributionViewModel classroomDsitributionViewModel) {
        this.classroomDsitributionViewModel = classroomDsitributionViewModel;
    }

    public StudentsInClassSessionController() {
    }

    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult) {
        PresentationControlFactory.getViewLayerController().getStudentsInClassSession(studentsResult);
    }



    public void refreshStudentsInClassRecordView(List<Pair<User, Pair<Integer,Integer>>> r, boolean b) {
        studentsInClassRecordViewModel.setResponse(r,b);
    }

    public void refreshClassroomDistributionAssignments(List<Assignment> r, boolean b) {
        classroomDsitributionViewModel.setAssignmentsResponse(r,b);
    }

    public void getAssignmentsForClassSession(String classroomID, String date, String hour) {
        PresentationControlFactory.getViewLayerController().getAssignmentsForClassSession(classroomID,date,hour);
    }

    public void getStudentsInClassRecord(String classroomId, String date) {
        PresentationControlFactory.getViewLayerController().getStudentsInClassRecord(classroomId,date);
    }

    public void getClassroomInfo(String classroomID) {
        PresentationControlFactory.getViewLayerController().getClassroomInfo(classroomID);
    }

    public void refreshClassroomDistributionClassInfo(Classroom c, boolean b) {
        classroomDsitributionViewModel.setClassInfo(c,b);
    }
}
