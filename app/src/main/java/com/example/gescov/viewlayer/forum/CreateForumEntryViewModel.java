package com.example.gescov.viewlayer.forum;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.ArrayList;
import java.util.List;

public class CreateForumEntryViewModel extends ViewModel {
    private MutableLiveData<List<School>> list;

    public CreateForumEntryViewModel() {
        list = new MutableLiveData<List<School>>();
        list.setValue(new ArrayList<>());
    }

    public void setSchoolList(List<School> list) {
        User loggedUser = PresentationControlFactory.getViewLayerController().getLoggedUserInfo();
        List<School> adminSchoolList = new ArrayList<>();
        for (School school : list) {
            if (school.getAdministratorsList().contains(loggedUser.getId())) {
                adminSchoolList.add(school);
            }
        }
        this.list.setValue(adminSchoolList);
    }

    public MutableLiveData<List<School>> getSchoolList() {
        return list;
    }
}
