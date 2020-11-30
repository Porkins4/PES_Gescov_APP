package com.example.gescov.viewlayer.SchoolsActivities.studentschools.allSchools;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.SchoolsAdministrationFagment;

public class AllSchoolsListFragment extends SchoolsAdministrationFagment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        enableCreateSchoolButton(false);

        return v;
    }

    @Override
    protected void createSchoolListViewAdapter(Context context) {
        getController().createSchoolListViewAdapter(context);
    }

    @Override
    protected SchoolListViewAdapter getAdapter() {
        return getController().getSchoolListViewAdapter();
    }

}