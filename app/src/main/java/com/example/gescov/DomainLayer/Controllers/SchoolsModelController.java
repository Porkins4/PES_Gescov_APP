package com.example.gescov.DomainLayer.Controllers;

import com.example.gescov.DomainLayer.Classmodels.School;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SchoolsModelController {
    private List<School> schoolsList;

    public void setSchoolsList(String schoolsString) throws JSONException {
        JSONArray response = new JSONArray(schoolsString);
        schoolsList = new ArrayList();
        for (int i = 0; i < response.length(); ++i) {

            JSONObject aux = response.getJSONObject(i);
            String idSchool = aux.getString("id");
            String nameSchool = aux.getString("name");
            String stateSchool = aux.getString("state");
            String addressSchool = aux.getString("address");
            String creatorSchool = aux.getString("creator");
            schoolsList.add(new School(idSchool, nameSchool, addressSchool, stateSchool, creatorSchool));
        }
    }

    public List<School> getSchoolsList() {
        return schoolsList;
    }

}
