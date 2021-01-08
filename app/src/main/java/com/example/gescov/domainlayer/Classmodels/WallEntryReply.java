package com.example.gescov.domainlayer.Classmodels;

import com.example.gescov.GescovUtils;

public class WallEntryReply {
    private String time;
    private String text;
    private String username;

    public WallEntryReply (String time, String text, String username) {
        this.time = GescovUtils.getNormalizedTime(time);
        this.text = text;
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
