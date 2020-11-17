package com.example.gescov.ViewLayer.SchoolClassroomList;

import android.content.Context;

import com.example.gescov.DomainLayer.Classmodels.Classroom;
import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.ViewLayer.Exceptions.AdapterNotSetException;
import com.example.gescov.ViewLayer.PresentationControlFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class SchoolClassroomsCrontroller {

    private ClassroomListViewAdapter classroomListViewAdapter;
    private List<Classroom> classroomsList;

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

    public List<Classroom> getList() {
        if (classroomsList != null)
            return classroomsList;
        //hardcodedSchoolList();
        classroomsList = new ArrayList<>();
        return classroomsList;
    }

    public void refreshList() {
        PresentationControlFactory.getViewLayerController().getSchoolClassrooms(PresentationControlFactory.getSchoolsCrontroller().getCurrentSchool().getName());
    }

    public void refreshList(List<Classroom> classroomsList) {
        this.classroomsList = classroomsList;
        getClassroomListViewSchoolAdapter().setList(classroomsList);
        getClassroomListViewSchoolAdapter().notifyDataSetChanged();
    }


    private void hardcodedSchoolList () {
        classroomsList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            classroomsList.add(new Classroom("id", "Aula" + i, 6, 6, 30));
        }
    }



}