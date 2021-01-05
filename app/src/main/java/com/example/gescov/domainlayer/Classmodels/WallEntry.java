package com.example.gescov.domainlayer.Classmodels;

import java.util.List;

public class WallEntry {


    private List<WallEntryReply> replies;
    private String content;
    private String hour;
    private String id;
    private String title;
    private String schoolId;

    public WallEntry(String id, String title, String content, String hour, String schoolId, List<WallEntryReply> replies) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.hour = hour;
        this.schoolId = schoolId;
        this.replies = replies;
    }

    public List<WallEntryReply> getReplies() {
        return replies;
    }

    public void setReplies(List<WallEntryReply> replies) {
        this.replies = replies;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
