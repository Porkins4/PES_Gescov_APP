package com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassSession;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassRecord.StudentsInClassRecordActivity;
import com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassRecord.StudentsInClassRecordViewModel;
import com.example.gescov.ViewLayer.ViewLayerSingletons.PresentationControlFactory;

import java.util.List;

public class StudentsInClassSessionController {

    private StudentsInClassRecordViewModel studentsInClassRecordViewModel; //hacerlo también para el otro view

    public void setStudentsInClassRecordViewModel(StudentsInClassRecordViewModel studentsInClassRecordActivity) {
        this.studentsInClassRecordViewModel = studentsInClassRecordActivity;
    }

    public StudentsInClassSessionController() {
    }

    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult) {
        PresentationControlFactory.getViewLayerController().getStudentsInClassSession(studentsResult);
    }

    public void getStudentsInClassRecord(String classroomId, String date) {
        PresentationControlFactory.getViewLayerController().getStudentsInClassRecord(classroomId,date);
    }

    public void refreshStudentsInClassRecordView(List<Pair<String, String>> r, boolean b) {
        studentsInClassRecordViewModel.setResponse(r,b);
    }
}
