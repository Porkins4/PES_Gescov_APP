package com.example.gescov.ViewLayer.SchoolsAdministration;

import org.json.JSONException;

import java.util.List;

interface ISchoolsCrontroller {
    public void createSchool(String schoolName, String schoolCode);

    public SchoolListViewAdapter getSchoolListViewSchoolAdapter();
    public List<String> getSchoolNamesList();

    void refresh() throws JSONException;
}
