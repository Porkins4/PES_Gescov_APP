package com.example.gescov.domainlayer.Classmodels;

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

    public MessageModel() {}

    public MessageModel(String chatID, String creatorID, String date, String hour, String id, String text) {
        this.chatID = chatID;
        this.creatorID = creatorID;
        this.date = date;
        this.hour = hour;
        this.id = id;
        this.text = text;
    }

    public static MessageModel fromJSONtoMessage(JSONObject response) {
        MessageModel result = new MessageModel();
        try {
            String chatID = response.getString("chatID");
            String creatorID = response.getString("creatorID");
            //String creator = response.getString("creator");
            String date = response.getString("date");
            String hour = response.getString("hour");
            String id = response.getString("id");
            String text = response.getString("text");
            result = new MessageModel(chatID, creatorID, date, hour, id, text);
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

    public String getChatID() {
        return chatID;
    }

    public String getHour() {
        return hour;
    }

    public void print() {
        System.out.println(chatID);
        System.out.println(creatorID);
        System.out.println(date);
        System.out.println(hour);
        System.out.println(text);
    }
}
