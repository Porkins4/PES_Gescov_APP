package com.example.gescov.DomainLayer.Controllers;

import android.util.Pair;

import com.example.gescov.DomainLayer.Classmodels.Classroom;
import com.example.gescov.DomainLayer.DomainLayerSingletons.DomainControlFactory;
import com.example.gescov.DomainLayer.Services.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SchoolClassroomsModelController {



    public void setClassroomList(String classroomsString) {
        JSONArray response = null;
        List<Classroom> classroomsList = new ArrayList();
        try {
            response = new JSONArray(classroomsString);
            for (int i = 0; i < response.length(); ++i) {

                JSONObject aux = response.getJSONObject(i);
                String id = aux.getString("id");
                String name = aux.getString("name");
                int rows = aux.getInt("numRows");
                int columns = aux.getInt("numCols");
                int capacity = aux.getInt("capacity");
                classroomsList.add(new Classroom(id, name, rows, columns, capacity));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DomainControlFactory.getModelController().refreshSchoolClassroomsListInView(classroomsList);
    }

    public void getStudentsInClassRecord(String classroomId, String date) {
        ServicesFactory.getClassroomService().getStudentsInClassRecord(classroomId,date);
    }

    public void updateStudentsInClassRecordView(JSONArray response,boolean b) {
        List<Pair<String,String>> r = new ArrayList<>();
        if (!b) {
            for (int i = 0; i < response.length(); ++i) {
                try {
                    JSONObject x = response.getJSONObject(i);
                    Pair<String, String> studentInfo = new Pair<>(x.getString("studentID"), "alumne assegut a columna: " + x.getString("posCol") + ", fila: " + x.getString("posRow"));
                    r.add(studentInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        DomainControlFactory.getModelController().refreshStudentsInClassRecordView(r,b);
    }
}
