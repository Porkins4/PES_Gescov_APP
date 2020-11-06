package com.example.gescov.ViewLayer.StudentsInClassSession;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.ViewLayer.PresentationControlFactory;

public class StudentsInClassSessionViewModel extends ViewModel {
    private MutableLiveData<StudentsInClassSessionResult> studentsResult;

    public LiveData<StudentsInClassSessionResult> getStudents() {
        if (studentsResult == null) {
            studentsResult = new MutableLiveData<>();
            getStudentsFromCloud();
        }
        return studentsResult;
    }

    private void getStudentsFromCloud() {
        PresentationControlFactory.getStudentsInClassSessionController().getStudentsInClassSession(studentsResult);
    }

    public void update() {
        getStudentsFromCloud();
    }

    public void clearDataset() {
        StudentsInClassSessionResult k = studentsResult.getValue();
        k.getStudentNames().clear();
    }
}
