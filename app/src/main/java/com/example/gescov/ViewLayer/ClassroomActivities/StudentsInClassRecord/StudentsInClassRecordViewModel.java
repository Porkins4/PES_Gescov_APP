package com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassRecord;

import android.util.Pair;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.toolbox.StringRequest;
import com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.ViewLayer.ViewLayerSingletons.PresentationControlFactory;

import java.util.List;

public class StudentsInClassRecordViewModel extends ViewModel {

    private MutableLiveData<StudentsInClassRecordResult> studentsResult;

    public LiveData<StudentsInClassRecordResult> getStudents(String classroomID, String date) {
        if (studentsResult == null) {
            studentsResult = new MutableLiveData<>();
            getStudentsInClassRecord(classroomID,date);
        }
        return studentsResult;
    }

    private void getStudentsInClassRecord(String classroomId, String date) {
        PresentationControlFactory.getStudentsInClassSessionController().setStudentsInClassRecordViewModel(this);
        PresentationControlFactory.getStudentsInClassSessionController().getStudentsInClassRecord(classroomId,date);
    }

    public void setResponse(List<Pair<String, String>> r, boolean b) {
        StudentsInClassRecordResult result = new StudentsInClassRecordResult(r,b);
        studentsResult.setValue(result);
    }
}
