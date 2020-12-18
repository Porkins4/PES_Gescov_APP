package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;


public class SubjectViewModel extends ViewModel {
    private SubjectAdapter adapter;
    private MutableLiveData<Boolean> received;
    private List<Subject> subjects;


    public SubjectAdapter getAdapter(Context c) {
        adapter = new SubjectAdapter(c, subjects);
        return adapter;
    }


    public MutableLiveData<Boolean> getSubjects(String schooldID) {
        received = new MutableLiveData<>();
        PresentationControlFactory.getSubjectController().setSubjectViewModel(this);
        PresentationControlFactory.getSubjectController().getSubjects(schooldID);

        return received;

    }

    public void sendResponseOfSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        received.setValue(true);
    }
}
