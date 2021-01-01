package com.example.gescov.viewlayer.forum;

import com.example.gescov.domainlayer.Classmodels.School;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreateForumEntryViewModel extends ViewModel {
    private MutableLiveData<List<School>> list;

    public CreateForumEntryViewModel() {
        list = new MutableLiveData<List<School>>();
        list.setValue(new ArrayList<>());
    }

    public void setSchoolList(List<School> list) {
        this.list.setValue(list);
    }

    public MutableLiveData<List<School>> getSchoolList() {
        return list;
    }
}
