package com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

public class StudentsInClassSessionViewModel extends ViewModel {
    private MutableLiveData<StudentsInClassSessionResult> studentsResult;
    private String classSession;

    public LiveData<StudentsInClassSessionResult> getStudents() {
        if (studentsResult == null) {
            studentsResult = new MutableLiveData<>();
            PresentationControlFactory.getStudentsInClassSessionController().getStudentsInClassSession(studentsResult, classSession);
        }
        return studentsResult;
    }

    public void update() {
        PresentationControlFactory.getStudentsInClassSessionController().getStudentsInClassSession(studentsResult, classSession);
    }

    public void clearDataset() {
        StudentsInClassSessionResult k = studentsResult.getValue();
        k.getStudentNames().clear();
    }

    public void init(String classSession) {
        this.classSession = classSession;
    }
}
