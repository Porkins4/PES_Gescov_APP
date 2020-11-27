package com.example.gescov.DomainLayer.Controllers;

import com.example.gescov.DomainLayer.Classmodels.SchoolRequest;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;
import com.example.gescov.DomainLayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SchoolRequestModelController {
    private List<SchoolRequest> schoolRequestsList;

    public SchoolRequestModelController () {
        schoolRequestsList = new ArrayList<>();
    }

    public void getSchoolRequestsBySchoolId(String schoolId) {
        ServicesFactory.getRefreshSchoolRequestsBySchoolIdResponseController().refreshSchoolClassroomsList(schoolId);
    }

    public void setSchoolRequestsList(String listString) {
        JSONArray response = new JSONArray();
        try {
            response = new JSONArray(listString);
            for (int i = 0; i < response.length(); ++i) {

                JSONObject aux = response.getJSONObject(i);
                String id = aux.getString("id");
                String schoolId = aux.getString("schoolID");
                String userId = aux.getString("userID");
                String status = aux.getString("status");
                String requestDate = aux.getString("requestDate");
                schoolRequestsList.add(new SchoolRequest(id, schoolId, userId, status, requestDate));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        DomainControlFactory.getModelController().refreshSchoolRequestsInView(schoolRequestsList);
    }
}
