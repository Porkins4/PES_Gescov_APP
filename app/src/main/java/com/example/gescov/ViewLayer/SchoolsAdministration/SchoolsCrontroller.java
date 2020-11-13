package com.example.gescov.ViewLayer.SchoolsAdministration;

import android.content.Context;
import android.widget.Adapter;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.ViewLayer.Exceptions.AdapterNotSetException;
import com.example.gescov.ViewLayer.PresentationControlFactory;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SchoolsCrontroller{

    private SchoolListViewAdapter schoolListViewAdapter;
    private List<School> schoolsList;

    private SchoolsAdministrationFagment fragment;
    private HashMap<String, School> schoolHash;
    private School currentSchool;

    public void setSchoolsAdministrationFragment(SchoolsAdministrationFagment fragment) {
        this.fragment = fragment;
    }

    public SchoolsAdministrationFagment getSchoolsAdministrationFragment() {
        return fragment;
    }

    public void createSchoolListViewAdapter(Context context) {
        schoolListViewAdapter = new SchoolListViewAdapter(context, getSchoolsList());
    }

    public SchoolListViewAdapter getSchoolListViewAdapter() throws AdapterNotSetException {
        if (schoolListViewAdapter == null) {
            throw new AdapterNotSetException();
        }
        return schoolListViewAdapter;
    }

    public List<School> getSchoolsList() {
        if (schoolsList != null)
            return schoolsList;
        //hardcodedSchoolList();
        schoolsList = new ArrayList<>();
        return schoolsList;
    }

    public void refreshSchoolsList() throws JSONException, AdapterNotSetException {
        List<School> schoolsList = PresentationControlFactory.getViewLayerController().getAllSchools();
        this.schoolHash = new HashMap<>();
        this.schoolsList = schoolsList;
        for (School school : schoolsList) {
            schoolHash.put(school.getName(), school);
        }
        getSchoolListViewAdapter().notifyDataSetChanged();
    }

    public void createSchool(String schoolName, String schoolAddress, String schoolState, String schoolWebsite) {
        PresentationControlFactory.getViewLayerController().createSchool(schoolName, schoolAddress, schoolState, schoolWebsite);
    }

    public void setCurrentSchool(String currentSchool) {
        this.currentSchool = schoolHash.get(currentSchool);
    }

    public School getCurrentSchool() {
        return currentSchool;
    }

    public void deleteSchool(School school) {
        PresentationControlFactory.getViewLayerController().deleteSchool(school);
        try {
            refreshSchoolsList();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (AdapterNotSetException e) {
            e.printStackTrace();
        }
    }
}
