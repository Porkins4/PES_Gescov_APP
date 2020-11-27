package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.viewlayer.Exceptions.AdapterNotSetException;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolRequestResult;

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
        PresentationControlFactory.getViewLayerController().getAllSchools();
    }

    public void refreshSchoolsList(List<School> schoolsList) throws JSONException, AdapterNotSetException {
        this.schoolHash = new HashMap<>();
        this.schoolsList = schoolsList;
        for (School school : schoolsList) {
            schoolHash.put(school.getName(), school);
        }
        getSchoolListViewAdapter().setSchoolList(schoolsList);
        getSchoolListViewAdapter().notifyDataSetChanged();
    }

    public void createSchool(String schoolName, String schoolAddress, String schoolTelephone, String schoolWebsite) {
        PresentationControlFactory.getViewLayerController().createSchool(schoolName, schoolAddress, schoolTelephone, schoolWebsite);
    }

    public void setCurrentSchool(String currentSchool) {
        this.currentSchool = schoolHash.get(currentSchool);
        PresentationControlFactory.getViewLayerController().setCurrentSchool(this.currentSchool);
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

    public void addStudentToCenter(String schoolName, MutableLiveData<SchoolRequestResult> result) {
        PresentationControlFactory.getViewLayerController().addStudentToCenter(schoolName,result);
    }
}