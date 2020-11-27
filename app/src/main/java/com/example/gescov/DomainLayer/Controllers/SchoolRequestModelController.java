package com.example.gescov.DomainLayer.Controllers;

import com.example.gescov.DomainLayer.Classmodels.SchoolRequest;
import com.example.gescov.DomainLayer.Classmodels.User;
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
        schoolRequestsList = new ArrayList<>();
        JSONArray response = new JSONArray();
        try {
            response = new JSONArray(listString);
            for (int i = 0; i < response.length(); ++i) {
                JSONObject aux = response.getJSONObject(i);
                JSONObject jsonSchoolRequest = aux.getJSONObject("first");

                String userName = aux.getString("second");
                String id = jsonSchoolRequest.getString("id");
                String schoolId = jsonSchoolRequest.getString("schoolID");
                String userId = jsonSchoolRequest.getString("userID");
                String status = jsonSchoolRequest.getString("status");
                String requestDate = jsonSchoolRequest.getString("requestDate");
                schoolRequestsList.add(new SchoolRequest(id, schoolId, userId, userName, status, requestDate));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        DomainControlFactory.getModelController().refreshSchoolRequestsInView(schoolRequestsList);
    }

    public void updateSchoolRequestStatus(String status, String schoolRequestId) {
        User currentUser = DomainControlFactory.getUserModelController().getLoggedUser();
        ServicesFactory.getUpdateSchoolRequestStatusResponseController().updateSchoolRequestStatus(status, schoolRequestId, currentUser.getId());
    }
}
