package com.example.gescov.domainlayer.Controllers;

import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.domainlayer.Classmodels.WallEntry;
import com.example.gescov.domainlayer.Classmodels.WallEntryReply;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.domainlayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ForumModelController {
    List<WallEntry> wallEntryList;

    public void createForumEntry(String titleEntry, String textEntry, String schoolId) {
        User user = DomainControlFactory.getModelController().getLoggedUser();
        ServicesFactory.getCreateForumEntryResponseController().createForumEntry(titleEntry, textEntry, schoolId, user.getId());
    }

    public void addSchoolWallEntries(String schoolWallEntries) {
        try {
            JSONArray response = new JSONArray(schoolWallEntries);
            for (int i = 0; i < response.length(); ++i) {

                JSONObject aux = response.getJSONObject(i);
                wallEntryList.add(getWallEntryFromJSON(aux));
                DomainControlFactory.getModelController().refreshLoggedUserSchoolsWallEntries(wallEntryList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public WallEntry getWallEntryFromJSON(JSONObject jsonWE) {
        try {
            String id = jsonWE.getString("id");
            String hour = jsonWE.getString("hour");
            String title = jsonWE.getString("title");
            String schoolId = jsonWE.getString("schoolID");
            String content = jsonWE.getString("text");
            List<WallEntryReply> replies = getRepliesFromJSON(jsonWE.getJSONArray("replies"));
            return new WallEntry(id, title, content, hour, schoolId, replies);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<WallEntryReply> getRepliesFromJSON(JSONArray wallEntryRepliesArray) {
        List<WallEntryReply> list = new ArrayList<>();
        try {
            for (int i = 0; i < wallEntryRepliesArray.length(); ++i) {

                JSONObject aux = wallEntryRepliesArray.getJSONObject(i);
                String username = aux.getString("username");
                String hour = aux.getString("hour");
                String content = aux.getString("text");

                list.add(new WallEntryReply(hour, content, username));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void refreshLoggedUserSchoolsWallEntries() {
        wallEntryList = new ArrayList<>();
        List schools = DomainControlFactory.getModelController().getLoggedInUser().getSchoolsID();
        for (String id : DomainControlFactory.getModelController().getLoggedInUser().getSchoolsID()) {
            ServicesFactory.getRefreshWallEntriesBySchoolIdResponseController().refreshSchoolWallEntries(id);
        }
    }

    public void createForumEntryReply(String wallEntryId, String content, String userId) {
        ServicesFactory.getCreateForumEntryReplyResponseController().createForumEntryReply(content, wallEntryId, userId);
    }

    public void refreshWallEntry(WallEntry wallEntry) {
        for (WallEntry we : wallEntryList) {
            if (we.getId().equals(wallEntry.getId())) {
                we = wallEntry;
                return;
            }
        }
        wallEntryList.add(wallEntry);
    }

    public void refreshWallEntryReplies(WallEntry wallEntry) {
        refreshWallEntry(wallEntry);
        DomainControlFactory.getModelController().refreshWallEntryReplies(wallEntry);
    }
}
