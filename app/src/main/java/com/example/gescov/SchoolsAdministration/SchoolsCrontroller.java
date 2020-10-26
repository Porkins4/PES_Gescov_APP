package com.example.gescov.SchoolsAdministration;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class SchoolsCrontroller implements ISchoolsCrontroller{

    private SchoolListViewAdapter schoolListViewAdapter;
    private List<String> schoolNamesList;

    @Override
    public void createSchool(String schoolName, String schoolCode) {

    }

    public void setSchoolListViewAdapter(Context context) {
        schoolListViewAdapter = new SchoolListViewAdapter(context, getSchoolNamesList());
    }

    @Override
    public SchoolListViewAdapter getSchoolListViewSchoolAdapter() {
        return schoolListViewAdapter;
    }

    @Override
    public List<String> getSchoolNamesList() {
        if (schoolNamesList != null)
            return schoolNamesList;
        hardcodedSchoolList();
        return schoolNamesList;
    }

    private void hardcodedSchoolList () {
        schoolNamesList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            schoolNamesList.add("Escola de perdedors");
        }
    }
}
