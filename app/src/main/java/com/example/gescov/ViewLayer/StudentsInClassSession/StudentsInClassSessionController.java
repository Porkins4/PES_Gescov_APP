package com.example.gescov.ViewLayer.StudentsInClassSession;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.ViewLayer.PresentationControlFactory;

public class StudentsInClassSessionController {

    public StudentsInClassSessionController() {
    }

    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult) {
        PresentationControlFactory.getViewLayerController().getStudentsInClassSession(studentsResult);
    }
}
