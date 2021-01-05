package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.checkclasssession;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.ClassSessionModel;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class CheckClassSessionViewModel extends ViewModel {

    private MutableLiveData<Boolean> response;
    private String subjectID;
    private List<ClassSessionModel> classSessions;

    public CheckClassSessionViewModel() {
        PresentationControlFactory.getSubjectController().setCheckClassSessionViewModel(this);
    }

    public void init(String subjectID) {
        this.subjectID = subjectID;
    }

    public LiveData<Boolean> getClassSessions() {
        if (response == null) response = new MutableLiveData<>();
        PresentationControlFactory.getSubjectController().getClassSessions(subjectID);
        return response;
    }

    public void setClassSessionsResult(boolean error, List<ClassSessionModel> classSessions) {
        this.classSessions = classSessions;
        response.setValue(error);
    }

    public ClassSessionAdapter getAdapter(Context c) {
        return new ClassSessionAdapter(c,classSessions);
    }

    public String getClassroomID(int position) {
        return classSessions.get(position).getClassroomID();
    }

    public String getClassroomSessionID(int position) {
        return classSessions.get(position).getId();
    }
}
