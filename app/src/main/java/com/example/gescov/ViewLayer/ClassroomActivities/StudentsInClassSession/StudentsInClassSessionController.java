package com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassSession;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.ViewLayer.ViewLayerSingletons.PresentationControlFactory;

public class StudentsInClassSessionController {

    public StudentsInClassSessionController() {
    }

    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult) {
        PresentationControlFactory.getViewLayerController().getStudentsInClassSession(studentsResult);
    }
}
