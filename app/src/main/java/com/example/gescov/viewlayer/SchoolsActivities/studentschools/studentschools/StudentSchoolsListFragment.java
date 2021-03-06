package com.example.gescov.viewlayer.SchoolsActivities.studentschools.studentschools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.AccessSchoolByCodeActivity;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.SchoolsAdministrationFagment;
import com.example.gescov.viewlayer.SchoolsActivities.studentschools.allSchools.SchoolListViewAdapter;

public class StudentSchoolsListFragment extends SchoolsAdministrationFagment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        return v;
    }

    @Override
    protected void refreshSchoolsList() {
        getController().refreshStudentSchoolsList();
    }

    @Override
    protected void createSchoolListViewAdapter(Context context) {
        getController().createSchoolListViewAdapter(context);
    }

    @Override
    protected SchoolListViewAdapter getAdapter() {
        return getController().getSchoolListViewAdapter();
    }

    @Override
    protected void setCreateButtonActions() {
        Intent intent = new Intent(getActivity(), AccessSchoolByCodeActivity.class);
        intent.putExtra("schoolId", "");
        startActivity(intent);
    }
}
