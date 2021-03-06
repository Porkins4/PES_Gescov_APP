package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.createsubject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.GescovUtils;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

public class CreateSubjectViewModel extends ViewModel {

    private MutableLiveData<Boolean> response;
    private String schoolID;
    private int responseCode;

    public CreateSubjectViewModel() {
        PresentationControlFactory.getSubjectController().setCreateSubjectViewModel(this);
    }


    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }


    public LiveData<Boolean> createSchool(String subjectName) {
        if (response == null) response = new MutableLiveData<>();
        PresentationControlFactory.getSubjectController().createSubject(subjectName, schoolID);
        return response;
    }

    public void setCreateSubjectResult(boolean error, int responseCode) {
        this.responseCode = responseCode;
        response.setValue(error);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void updateUserSubjects() {
        GescovUtils.updateSubjects();
    }
}
