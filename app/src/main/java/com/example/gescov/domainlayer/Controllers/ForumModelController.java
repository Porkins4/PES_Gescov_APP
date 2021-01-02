package com.example.gescov.domainlayer.Controllers;

import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.domainlayer.Classmodels.WallEntry;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.domainlayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ForumModelController {
    List<WallEntry> wallEntryList;

    public void createForumEntry(String textEntry, String schoolId) {
        User user = DomainControlFactory.getModelController().getLoggedUser();
        ServicesFactory.getCreateForumEntryResponseController().createForumEntry(textEntry, schoolId, user.getId());
    }

    public void addSchoolWallEntries(String schoolWallEntries) {
        try {
            JSONArray response = new JSONArray(schoolWallEntries);
            for (int i = 0; i < response.length(); ++i) {

                JSONObject aux = response.getJSONObject(i);
                String id = aux.getString("id");
                String hour = aux.getString("hour");
                String schoolId = aux.getString("schoolID");
                String content = aux.getString("text");

                wallEntryList.add(new WallEntry(id, content, hour, schoolId));
                DomainControlFactory.getModelController().refreshLoggedUserSchoolsWallEntries(wallEntryList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void refreshLoggedUserSchoolsWallEntries() {
        wallEntryList = new ArrayList<>();
        for (String id : DomainControlFactory.getModelController().getLoggedInUser().getSchoolsID()) {
            ServicesFactory.getRefreshWallEntriesBySchoolIdResponseController().refreshSchoolWallEntries(id);
        }
    }
}
