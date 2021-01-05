package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.subjects;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;


public class SubjectViewModel extends ViewModel {
    private SubjectAdapter adapter;
    private MutableLiveData<Boolean> received;
    private MutableLiveData<Boolean> assignedStudent;
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

    public MutableLiveData<Boolean> assignStudent(String subjectID) {
        assignedStudent = new MutableLiveData<>();
        PresentationControlFactory.getSubjectController().setSubjectViewModel(this);
        PresentationControlFactory.getSubjectController().assignStudent(subjectID,1);
        return  assignedStudent;
    }

    public void notifyAssignStudent(boolean error) {
        assignedStudent.setValue(error);
    }

    public User getLoggedInUser() {
        return PresentationControlFactory.getViewLayerController().getUserLoggedIn();
    }
}
