package com.example.gescov.ViewLayer.SchoolClassroomList;

import android.content.Context;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolsCrontroller;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class SchoolClassroomsCrontroller {

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

    public void createClassroom(String classroomName, String classrooomCapacity, String classroomRows, String classroomCols) {
        School currentSchool = PresentationControlFactory.getSchoolsCrontroller().getCurrentSchool();
        PresentationControlFactory.getViewLayerController().createClassroom(currentSchool, classroomName, classrooomCapacity, classroomRows, classroomCols);
    }

    public ClassroomListViewAdapter getClassroomListViewSchoolAdapter() {
        return classroomListViewAdapter;
    }

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
