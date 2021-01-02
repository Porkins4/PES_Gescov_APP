package com.example.gescov.viewlayer.subjectsofuser;

import android.content.Context;
import android.widget.ListAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class SubjectsFromUserViewModel extends ViewModel {

    private MutableLiveData<Boolean> result;
    private List<Subject> subjects;
    private SubjectsFromUserAdapter adapter;

    public SubjectsFromUserViewModel() {
        PresentationControlFactory.getSubjectController().setSubjectsFromUserViewModel(this);
    }

    public LiveData<Boolean> getSubjectsFromUser() {
        if (result == null) result = new MutableLiveData<>();
        PresentationControlFactory.getSubjectController().getSubjectsFromUser();
        return result;
    }

    public void setSubjectsFromUserResult(boolean error, List<Subject> userSubjects) {
        if (!error) {
            subjects = userSubjects;
        }
        result.setValue(error);
    }

    public boolean resultEmpty() {
        return subjects.isEmpty();
    }

    public SubjectsFromUserAdapter getAdapter(Context c) {
        adapter = new SubjectsFromUserAdapter(c,subjects);
        return adapter;
    }

    public String getSubjectID(int position) {
        return subjects.get(position).getId();
    }

    public String getSchoolID(int position) {
        return subjects.get(position).getSchoolID();
    }

    public String getSubjectName(int position) {
        return subjects.get(position).getName();
    }
}
