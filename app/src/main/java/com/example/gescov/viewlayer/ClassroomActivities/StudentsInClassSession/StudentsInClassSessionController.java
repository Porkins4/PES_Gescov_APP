package com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.domainlayer.Classmodels.Assignment;
import com.example.gescov.domainlayer.Classmodels.Classroom;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.ClassroomActivities.ClassroomDistribution.ClassroomDsitributionViewModel;
import com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassRecord.StudentsInClassRecordViewModel;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

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

    public StudentsInClassSessionController() {//constructor without colateral actions
    }

    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult, String classSession) {
        PresentationControlFactory.getViewLayerController().getStudentsInClassSession(studentsResult, classSession);
    }



    public void refreshStudentsInClassRecordView(List<Pair<User, Pair<Integer,Integer>>> r, List<String> dates, boolean b) {
        studentsInClassRecordViewModel.setResponse(r,dates,b);
    }

    public void refreshClassroomDistributionAssignments(List<Assignment> r, boolean b) {
        classroomDsitributionViewModel.setAssignmentsResponse(r,b);
    }

    public void getAssignmentsForClassSession(String classSessionID) {
        PresentationControlFactory.getViewLayerController().getAssignmentsForClassSession(classSessionID);
    }

    public void getStudentsInClassRecord(String classroomId) {
        PresentationControlFactory.getViewLayerController().getStudentsInClassRecord(classroomId);
    }

    public void getClassroomInfo(String classroomID) {
        PresentationControlFactory.getViewLayerController().getClassroomInfo(classroomID);
    }

    public void refreshClassroomDistributionClassInfo(Classroom c, boolean b) {
        classroomDsitributionViewModel.setClassInfo(c,b);
    }

    public void sendReservationRequest(String aula, int row, int col) {
        PresentationControlFactory.getViewLayerController().sendReservationRequest(aula, row, col);
    }

    public void setSendReservationRequestResponse(boolean error, int errorCode) {
        classroomDsitributionViewModel.setSendReservationRequestResponse(error,errorCode);
    }
}
