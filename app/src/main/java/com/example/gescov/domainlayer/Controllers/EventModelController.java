package com.example.gescov.domainlayer.Controllers;

import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.domainlayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventModelController {
    public void getGuests(String subjectID) {
        ServicesFactory.getEventService().getGuests(subjectID);
    }

    public void sendResponseOfGuests(JSONArray response) {
        List<User> guests = new ArrayList<>();
        for (int i = 0; i < response.length(); ++i) {
            try {
                JSONObject element = response.getJSONObject(i);
                String email = element.getString("email");
                String id = element.getString("id");
                String name = element.getString("name");
                String pic = element.getString("pic");
                Boolean risk = element.getBoolean("risk");
                Boolean isStudent = element.getBoolean("student");
                String tokenID = element.getString("tokenID");
                JSONArray schoolsID = element.getJSONArray("schoolsID");
                List<String> schools = new ArrayList<>();
                for (int j = 0; j < schoolsID.length(); ++j) {
                    String school = schoolsID.getString(j);
                    schools.add(school);
                }
                //String name, String id, List<String> schools, boolean risk, boolean isStudent, String email, String tokenId, String pic
                User user = new User(name,id,schools,risk,isStudent,email,tokenID,pic);
                guests.add(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        DomainControlFactory.getModelController().sendResponseOfGuests(guests);
    }
}
