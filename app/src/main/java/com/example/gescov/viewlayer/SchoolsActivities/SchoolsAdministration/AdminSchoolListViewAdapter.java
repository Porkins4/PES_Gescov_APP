package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import android.content.Context;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.viewlayer.SchoolsActivities.studentschools.allSchools.SchoolListViewAdapter;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.ArrayList;
import java.util.List;

public class AdminSchoolListViewAdapter extends SchoolListViewAdapter {
    public AdminSchoolListViewAdapter(Context context, List<School> schoolsList) {
        super(context, schoolsList);
    }

    @Override
    public void setSchoolList(List<School> schoolList) {
        User loggedUser = PresentationControlFactory.getViewLayerController().getLoggedUserInfo();
        List<School> adminSchoolList = new ArrayList<>();
        for (School school : schoolList) {
            if (school.getAdministratorsList().contains(loggedUser.getId())) {
                adminSchoolList.add(school);
            }
        }
        super.setSchoolList(adminSchoolList);
    }
}
