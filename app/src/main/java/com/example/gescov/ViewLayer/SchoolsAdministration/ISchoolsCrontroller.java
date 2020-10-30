package com.example.gescov.ViewLayer.SchoolsAdministration;

import org.json.JSONException;

import java.util.List;

interface ISchoolsCrontroller {
    public void createSchool(String schoolName, String schoolCode);

    public SchoolListViewAdapter getSchoolListViewSchoolAdapter();
    public List<String> getSchoolNamesList();
    public void createSchool1 ( String schoolName , String schoolAddress );
    void refresh() throws JSONException;
}
