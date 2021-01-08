package com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassRecord;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class StudentsInClassRecordViewModel extends ViewModel {

    private MutableLiveData<StudentsInClassRecordResult> studentsResult;

    public LiveData<StudentsInClassRecordResult> getStudents(String classroomID) {
        if (studentsResult == null) {
            studentsResult = new MutableLiveData<>();
            getStudentsInClassRecord(classroomID);
        }
        return studentsResult;
    }

    private void getStudentsInClassRecord(String classroomId) {
        PresentationControlFactory.getStudentsInClassSessionController().setStudentsInClassRecordViewModel(this);
        PresentationControlFactory.getStudentsInClassSessionController().getStudentsInClassRecord(classroomId);
    }

    public void setResponse(List<Pair<User, Pair<Integer,Integer>>> r, List<String> dates, boolean b) {
        StudentsInClassRecordResult result = new StudentsInClassRecordResult(r,dates,b);
        studentsResult.setValue(result);
    }
}
