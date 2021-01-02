package com.example.gescov.domainlayer.Classmodels;

public class WallEntry {


    private String content;
    private String hour;
    private String id;
    private String schoolId;

    public WallEntry(String id, String content, String hour, String schoolId) {
        this.id = id;
        this.content = content;
        this.hour = hour;
        this.schoolId = schoolId;
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

}
