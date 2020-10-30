package com.example.gescov.ViewLayer.SchoolsAdministration;

import android.content.Context;

import com.example.gescov.DomainLayer.Controllers.ModelController;
import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.ViewLayer.ViewLayerController;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class SchoolsCrontroller implements ISchoolsCrontroller{

    private SchoolListViewAdapter schoolListViewAdapter;
    private List<String> schoolNamesList;

    private SchoolsAdministrationFagment fragment;

    @Override
    public void createSchool(String schoolName, String schoolCode) {

    }

    public void setSchoolsAdministrationFragment(SchoolsAdministrationFagment fragment) {
        this.fragment = fragment;
    }

    public SchoolsAdministrationFagment getSchoolsAdministrationFragment() {
        return fragment;
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
        schoolNamesList = new ArrayList<>();
       //º hardcodedSchoolList();
        return schoolNamesList;
    }

    @Override
    public void createSchool1(String schoolName, String schoolAddress) {
        PresentationControlFactory.getViewLayerController().createSchool(schoolName,schoolAddress);

    }

    @Override
    public void refresh() throws JSONException {
        fragment.updateData(PresentationControlFactory.getViewLayerController().getAllSchools());
    }

    private void hardcodedSchoolList () {
        schoolNamesList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            schoolNamesList.add("Escola de perdedors");
        }
    }
}
