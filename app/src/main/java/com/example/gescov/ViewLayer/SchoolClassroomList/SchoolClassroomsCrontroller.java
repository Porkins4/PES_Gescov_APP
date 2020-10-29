package com.example.gescov.ViewLayer.SchoolClassroomList;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class SchoolClassroomsCrontroller implements ISchoolClassroomsCrontroller {

    private ClassroomListViewAdapter classroomListViewAdapter;
    private List<String> classroomNamesList;

    private AppCompatActivity classroomActivity;



    public void setSchoolClassroomListActivity (AppCompatActivity classroomActivity) {
        this.classroomActivity = classroomActivity;
    }

    public AppCompatActivity getSchoolsAdministrationActivity() {
        return classroomActivity;
    }

    public void setSchoolListViewAdapter(Context context) {
        classroomListViewAdapter = new ClassroomListViewAdapter(context, getList());
    }

    @Override
    public void createClassroom(String schoolName, String schoolCode) {

    }

    @Override
    public ClassroomListViewAdapter getClassroomListViewSchoolAdapter() {
        return classroomListViewAdapter;
    }

    @Override
    public List<String> getList() {
        if (classroomNamesList != null)
            return classroomNamesList;
        hardcodedSchoolList();
        return classroomNamesList;
    }

    private void hardcodedSchoolList () {
        classroomNamesList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            classroomNamesList.add("Aula" + i);
        }
    }
}
