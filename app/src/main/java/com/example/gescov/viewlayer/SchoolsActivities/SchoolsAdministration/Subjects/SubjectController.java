package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects;

import com.example.gescov.domainlayer.Classmodels.ClassSessionModel;
import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.addteachertosubject.AddTeacherToSubjectViewModel;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.checkclasssession.CheckClassSessionViewModel;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class SubjectController {

    private SubjectViewModel subjectViewModel;
    private AddTeacherToSubjectViewModel addTeacherToSubjectViewModel;
    private CheckClassSessionViewModel checkClassSessionViewModel;

    public void setSubjectViewModel(SubjectViewModel subjectViewModel) {
        this.subjectViewModel = subjectViewModel;
    }

    public void getSubjects(String schooldID) {
        PresentationControlFactory.getViewLayerController().getSubjects(schooldID);
    }

    public void sendResponseOfSubjects(List<Subject> subjects) {
        subjectViewModel.sendResponseOfSubjects(subjects);
    }

    public void assignStudent(String subjectID, int activityIdentifier) {
        PresentationControlFactory.getViewLayerController().assignStudentToSubject(subjectID, activityIdentifier);
    }

    public void notifyAssignStudent(boolean error) {
        subjectViewModel.notifyAssignStudent(error);
    }

    public void setAddTeacherToSubjectViewModel(AddTeacherToSubjectViewModel addTeacherToSubjectViewModel) {
        this.addTeacherToSubjectViewModel = addTeacherToSubjectViewModel;
    }

    public void getTeachersOfSchool(String schoolID) {
        PresentationControlFactory.getViewLayerController().getContactsFromCenter(schoolID, 2);
    }

    public void notifyListOfTeachersReceivedToAddSubject(List<User> contactsFromSelectedCenter) {
        this.addTeacherToSubjectViewModel.notifyListOfTeachersReceivedToAddSubject(contactsFromSelectedCenter);

    }

    public void assignTeacherToSubject(String id, String subjectID, int activityIdentifier) {
        PresentationControlFactory.getViewLayerController().assignTeacherToSubject(id,subjectID,activityIdentifier);
    }

    public void notifyAssignedTeacher(boolean error) {
        this.addTeacherToSubjectViewModel.notifyAssignedTeacher(error);
    }

    public void setCheckClassSessionViewModel(CheckClassSessionViewModel checkClassSessionViewModel) {
        this.checkClassSessionViewModel = checkClassSessionViewModel;
    }

    public void getClassSessions(String subjectID) {
        PresentationControlFactory.getViewLayerController().getClassSessions(subjectID);
    }

    public void getClassSessionsResult(boolean error, List<ClassSessionModel> classSessions) {
        this.checkClassSessionViewModel.setClassSessionsResult(error,classSessions);
    }
}
