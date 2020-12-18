package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects;

import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class SubjectController {
    private SubjectViewModel subjectViewModel;

    public void setSubjectViewModel(SubjectViewModel subjectViewModel) {
        this.subjectViewModel = subjectViewModel;
    }

    public void getSubjects(String schooldID) {
        PresentationControlFactory.getViewLayerController().getSubjects(schooldID);
    }

    public void sendResponseOfSubjects(List<Subject> subjects) {
        subjectViewModel.sendResponseOfSubjects(subjects);
    }

    public void assignStudent(String subjectID) {
        PresentationControlFactory.getViewLayerController().assignStudent(subjectID);
    }

    public void notifyAssignStudent(boolean error) {
        subjectViewModel.notifyAssignStudent(error);
    }
}
