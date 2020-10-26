package com.example.gescov.SchoolsAdministration;

import android.content.Context;

import java.util.List;

interface ISchoolsCrontroller {
    public void createSchool(String schoolName, String schoolCode);

    public SchoolListViewAdapter getSchoolListViewSchoolAdapter();
    public List<String> getSchoolNamesList();
}
