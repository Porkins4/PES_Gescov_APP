package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.addteachertosubject;

import android.content.Context;
import android.widget.ListAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class AddTeacherToSubjectViewModel extends ViewModel {

    private MutableLiveData<Boolean> teacherListReceived;
    private MutableLiveData<Boolean> teacherAssignedResult;
    private List<User> teachers;
    private TeacherSubjectAdapter adapter;

    public LiveData<Boolean> getTeacherList(String schoolID) {
        teacherListReceived = new MutableLiveData<>();
        getList(schoolID);
        return teacherListReceived;
    }

    private void getList(String schoolID) {
        PresentationControlFactory.getSubjectController().setAddTeacherToSubjectViewModel(this);
        PresentationControlFactory.getSubjectController().getTeachersOfSchool(schoolID);
    }

    public void notifyListOfTeachersReceivedToAddSubject(List<User> contactsFromSelectedCenter) {
        teachers = contactsFromSelectedCenter;
        teacherListReceived.setValue(true);
    }

    public ListAdapter getAdapter(Context c) {
        adapter = new TeacherSubjectAdapter(c,teachers);
        return adapter;
    }

    public LiveData<Boolean> assignTeacherToSubject(int position, String subjectID) {
        if (teacherAssignedResult == null) teacherAssignedResult = new MutableLiveData<>();
        User teacher = teachers.get(position);
        PresentationControlFactory.getSubjectController().assignTeacherToSubject(teacher.getId(),subjectID,2);
        return teacherAssignedResult;
    }

    public void notifyAssignedTeacher(boolean error) {
        teacherAssignedResult.setValue(error);
    }

}
