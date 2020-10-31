package com.example.gescov.ViewLayer.SchoolsAdministration;

import android.content.Context;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.ViewLayer.Exceptions.AdapterNotSetException;
import com.example.gescov.ViewLayer.PresentationControlFactory;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SchoolsCrontroller{

    private SchoolListViewAdapter schoolListViewAdapter;
    private List<String> schoolNamesList;

    private SchoolsAdministrationFagment fragment;
    private HashMap<String, School> schoolHash;
    private School currentSchool;

    public void setSchoolsAdministrationFragment(SchoolsAdministrationFagment fragment) {
        this.fragment = fragment;
    }

    public SchoolsAdministrationFagment getSchoolsAdministrationFragment() {
        return fragment;
    }

    public void setSchoolListViewAdapter(Context context) {
        schoolListViewAdapter = new SchoolListViewAdapter(context, getSchoolNamesList());
    }

    public SchoolListViewAdapter getSchoolListViewSchoolAdapter() throws AdapterNotSetException {
        if (schoolListViewAdapter == null) {
            throw new AdapterNotSetException();
        }
        return schoolListViewAdapter;
    }

    public List<String> getSchoolNamesList() {
        if (schoolNamesList != null)
            return schoolNamesList;
        //hardcodedSchoolList();
        schoolNamesList = new ArrayList<>();
        return schoolNamesList;
    }

    public void refreshSchoolsList() throws JSONException, AdapterNotSetException {
        List<School> schoolsList = PresentationControlFactory.getViewLayerController().getAllSchools();
        this.schoolHash = new HashMap<>();
        schoolNamesList = new ArrayList();
        for (School school : schoolsList) {
            schoolHash.put(school.getName(), school);
            schoolNamesList.add(school.getName());
        }
        getSchoolListViewSchoolAdapter().notifyDataSetChanged();
    }

    public void createSchool(String schoolId, String schoolName, String schoolAddress, String schoolState, String schoolCreator) {
        PresentationControlFactory.getViewLayerController().createSchool(schoolId, schoolName, schoolAddress, schoolState, schoolCreator);
    }

    private void hardcodedSchoolList () {
        schoolNamesList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            schoolNamesList.add("Escola de perdedors");
        }
    }

    public void setCurrentSchool(String currentSchool) {
        this.currentSchool = schoolHash.get(currentSchool);
    }

    public School getCurrentSchool() {
        return currentSchool;
    }
}
