package com.example.gescov.domainlayer.Classmodels;

public class WallEntryReply {
    private String hour;
    private String text;
    private String username;

    public WallEntryReply (String hour, String text, String username) {
        this.hour = hour;
        this.text = text;
        this.username = username;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
