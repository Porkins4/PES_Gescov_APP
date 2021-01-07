package com.example.gescov.domainlayer.Classmodels;

import com.example.gescov.GescovUtils;

import java.util.List;

public class WallEntry {


    private List<WallEntryReply> replies;
    private String content;
    private String time;
    private String id;
    private String title;
    private String schoolId;

    public WallEntry(String id, String title, String content, String time, String schoolId, List<WallEntryReply> replies) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.time = GescovUtils.getNormalizedTime(time);
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
