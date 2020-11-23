package com.example.gescov.DomainLayer.Controllers;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SchoolsModelController {
    private List<School> schoolsList;
    private School currentSchool;

    public void setSchoolsList(String schoolsString) throws JSONException {
        JSONArray response = new JSONArray(schoolsString);
        schoolsList = new ArrayList();
        for (int i = 0; i < response.length(); ++i) {

            JSONObject aux = response.getJSONObject(i);
            String idSchool = aux.getString("id");
            String nameSchool = aux.getString("name");
            String stateSchool = aux.getString("state");
            String addressSchool = aux.getString("address");
            String emailSchool = null;//aux.getString("email");
            String phone = aux.getString("phone");
            int longitude = aux.getInt("longitude");
            int latitude = aux.getInt("longitude");
            List<String> listAdminsID = new ArrayList<>();
            JSONArray adminsArray = aux.getJSONArray("administratorsID");
            for (int admin = 0; admin < adminsArray.length(); admin++) {
                listAdminsID.add(adminsArray.getString(admin));
            };
            String creatorSchoolID = aux.getString("creatorID");
            schoolsList.add(new School(idSchool, nameSchool, addressSchool, stateSchool, creatorSchoolID, emailSchool, phone, longitude, latitude, listAdminsID));
        }
    }

    public List<School> getAllSchools() throws JSONException {
        String schoolsString = DomainControlFactory.getUserController().getAllSchools();
        setSchoolsList(schoolsString);
        return getSchoolsList();
    }

    public void createSchool(String schoolName, String schoolAddress, String schoolTelephone, String schoolWebsite) {
        DomainControlFactory.getUserController().createSchool(schoolName,schoolAddress, schoolTelephone, schoolWebsite);
    }

    public List<School> getSchoolsList() {
        return schoolsList;
    }

    public void refreshSchoolList() {
        DomainControlFactory.getUserController().refreshSchoolList();
    }

    public void refreshSchoolList(String schoolsResponse) {
        try {
            setSchoolsList(schoolsResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DomainControlFactory.getModelController().refreshSchoolListInView(getSchoolsList());
    }

    public void setCurrentSchool(School currentSchool) {
        this.currentSchool = currentSchool;
    }

    public School getCurrentSchool() {
        return currentSchool;
    }

    public School getSchoolByName(String name) {
        Boolean found = false;
        School schoolaux = null;
        for ( int i = 0; i < schoolsList.size() && (!found) ; ++i) {
             schoolaux = schoolsList.get(i);
            if (schoolaux.getName() == name ) {
                found = true;
            }
        }
        return schoolaux;
    }

    public School getSchoolById(String id) {
        for (School school : schoolsList) {
            if (school.getId().equals(id))
                return school;
        }
        return null;
    }
}