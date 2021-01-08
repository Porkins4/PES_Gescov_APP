package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import android.content.Context;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.viewlayer.Exceptions.AdapterNotSetException;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolRequestResult;
import com.example.gescov.viewlayer.SchoolsActivities.studentschools.allSchools.SchoolListViewAdapter;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.forum.CreateForumEntryViewModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SchoolsCrontroller{

    private SchoolListViewAdapter schoolListViewAdapter;
    private SchoolListViewAdapter adminSchoolListViewAdapter;
    private List<School> schoolsList;

    private HashMap<String, School> schoolHash;

    private SchoolGraphViewModel schoolGraphViewModel;
    private SchoolCreateFormViewModel schoolCreateFormViewModel;
    private CreateForumEntryViewModel createForumEntryViewModel;


    public void createSchoolListViewAdapter(Context context) {
        schoolListViewAdapter = new SchoolListViewAdapter(context, getSchoolsList());
    }

    public SchoolListViewAdapter getSchoolListViewAdapter() {
        return schoolListViewAdapter;
    }

    public void createAdminSchoolListViewAdapter (Context context) {
        adminSchoolListViewAdapter = new AdminSchoolListViewAdapter(context, getSchoolsList());
    }

    public SchoolListViewAdapter getAdminSchoolListViewAdapter() {
        return adminSchoolListViewAdapter;
    }

    public List<School> getSchoolsList() {
        if (schoolsList != null)
            return schoolsList;
        schoolsList = new ArrayList<>();
        return schoolsList;
    }

    public void refreshAllSchoolsList() throws JSONException {
        PresentationControlFactory.getViewLayerController().refreshAllSchools();
    }
    public void refreshStudentSchoolsList() {
        PresentationControlFactory.getViewLayerController().refreshStudentSchools();
    }


    public void refreshSchoolsList(List<School> schoolsList){
        this.schoolHash = new HashMap<>();
        this.schoolsList = schoolsList;
        for (School school : schoolsList) {
            schoolHash.put(school.getName(), school);
        }
        SchoolListViewAdapter allSchoolsAdapter = getSchoolListViewAdapter();
        if (allSchoolsAdapter != null) {
            getSchoolListViewAdapter().setList(schoolsList);
            getSchoolListViewAdapter().notifyDataSetChanged();
        }

        SchoolListViewAdapter adminSchoolsAdapter = getAdminSchoolListViewAdapter();
        if (adminSchoolsAdapter != null) {
            getAdminSchoolListViewAdapter().setList(schoolsList);
            getAdminSchoolListViewAdapter().notifyDataSetChanged();
        }

        if (createForumEntryViewModel!= null) {
            createForumEntryViewModel.setSchoolList(schoolsList);
        }
    }

    public void createSchool(String schoolName, String schoolAddress, String schoolTelephone, String schoolWebsite, String latitude, String longitude) {
        PresentationControlFactory.getViewLayerController().createSchool(schoolName, schoolAddress, schoolTelephone, schoolWebsite, latitude, longitude);
    }

    public School getCurrentSchool() {
        return PresentationControlFactory.getViewLayerController().getCurrentSchool();
    }

    public void deleteSchool(School school) {
        PresentationControlFactory.getViewLayerController().deleteSchool(school);
        try {
            refreshAllSchoolsList();
        } catch (JSONException  e) {
            e.printStackTrace();
        }
    }

    public void addStudentToCenter(String schoolId, MutableLiveData<SchoolRequestResult> result) {
        PresentationControlFactory.getViewLayerController().addStudentToCenter(schoolId,result);
    }

    public void addNewAdminToSchool(String newAdminID) {
        PresentationControlFactory.getViewLayerController().addNewAdminToSchool(newAdminID);
    }


    public void setGraph(String schoolId) {
        PresentationControlFactory.getViewLayerController().setGraph(schoolId);
    }

    public void sendResponseOfGraph(List<Pair<String, Integer>> contagionPerMonth) {
        schoolGraphViewModel.setResponseOfGraph(contagionPerMonth);
    }

    public void setSchoolGraphModel(SchoolGraphViewModel schoolGraphViewModel) {
        this.schoolGraphViewModel = schoolGraphViewModel;
    }

    public SchoolCreateFormViewModel getSchoolCreateFormViewModel() {
        if (schoolCreateFormViewModel == null)
            schoolCreateFormViewModel = new SchoolCreateFormViewModel();
        return schoolCreateFormViewModel;
    }

    public CreateForumEntryViewModel getCreateForumEntryViewModel() {
        if (createForumEntryViewModel == null)
            createForumEntryViewModel = new CreateForumEntryViewModel();
        return createForumEntryViewModel;
    }

    public void deleteSchoolAdmin(String adminID) {
        PresentationControlFactory.getViewLayerController().deleteSchoolAdmin(adminID);
    }

    public void requestAcessSchoolByCode(String userId, String schoolId,  String schoolCode) {
        PresentationControlFactory.getViewLayerController().requestAcessSchoolByCode(userId, schoolId, schoolCode);
    }

    public void setCurrentSchool(School school) {
        PresentationControlFactory.getViewLayerController().setCurrentSchool(school);
    }

    public void updateCoordinatesSchoolCreationForm(String latitude, String longitude) {
        schoolCreateFormViewModel.updateCoordinates(latitude, longitude);
    }

    public School getSchoolById(String schoolId) {
        return PresentationControlFactory.getViewLayerController().getSchoolById(schoolId);
    }
}