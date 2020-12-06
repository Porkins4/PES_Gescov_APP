package com.example.gescov.domainlayer.Classmodels;

public class Assignment {
    private String studentName;
    private String studentId;
    private int row;
    private int col;

    public Assignment() {}

    public Assignment(String studentName, String studentId, int row, int col) {
        this.studentName = studentName;
        this. studentId = studentId;
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getStudentName() {
        return studentName;
    }
}
