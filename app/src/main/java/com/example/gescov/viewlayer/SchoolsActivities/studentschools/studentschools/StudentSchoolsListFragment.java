package com.example.gescov.viewlayer.SchoolsActivities.studentschools.studentschools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.SchoolsAdministrationFagment;

public class StudentSchoolsListFragment extends SchoolsAdministrationFagment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        enableCreateSchoolButton(false);

        return v;
    }

    @Override
    protected void refreshSchoolsList() {
        getController().refreshStudentSchoolsList();
    }
}
