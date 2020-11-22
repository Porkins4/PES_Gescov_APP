package com.example.gescov.ViewLayer.StudentsInClassSession;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.ViewLayer.StudentsInClassRecord.StudentsInClassRecordViewModel;
import com.example.gescov.ViewLayer.PresentationControlFactory;

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

    public void refreshStudentsInClassRecordView(List<Pair<User, Pair<Integer,Integer>>> r, boolean b) {
        studentsInClassRecordViewModel.setResponse(r,b);
    }
}
