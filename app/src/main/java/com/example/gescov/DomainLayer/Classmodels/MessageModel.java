package com.example.gescov.DomainLayer.Classmodels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageModel {
    private String chatID;
    private String creatorID;
    private String creator;
    private String date;
    private String hour;
    private String id;
    private String text;

    public static MessageModel fromJSONtoMessage(JSONObject response) {
        MessageModel result = new MessageModel();
        try {
            String chatID = response.getString("chatID");
            String creatorID = response.getString("creatorID");
            String creator = response.getString("creator");
            String date = response.getString("date");
            String hour = response.getString("hour");
            String id = response.getString("id");
            String text = response.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public String getText() {
        return text;
    }
}
