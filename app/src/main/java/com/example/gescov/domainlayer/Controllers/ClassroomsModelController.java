package com.example.gescov.domainlayer.Controllers;

import android.util.Pair;

import com.example.gescov.domainlayer.Classmodels.Classroom;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.domainlayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClassroomsModelController {

    private List<Classroom> classroomsFromCurrentSchool;

    public ClassroomsModelController() {
        classroomsFromCurrentSchool = new ArrayList<>();
    }

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
                String schoolID = aux.getString("schoolID");
                classroomsList.add(new Classroom(id, name, rows, columns,schoolID));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DomainControlFactory.getModelController().refreshSchoolClassroomsListInView(classroomsList);
    }

    public void getStudentsInClassRecord(String classroomId) {
        ServicesFactory.getClassroomService().getStudentsInClassRecord(classroomId);
    }

    public void updateStudentsInClassRecordView(JSONArray response, boolean b) {
        List<Pair<User, Pair<Integer,Integer>>> r = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        if (!b) {
            for (int i = 0; i < response.length(); ++i) {
                try {
                    JSONObject x = response.getJSONObject(i);
                    JSONObject classSessionInfo = new JSONObject(x.getString("first"));
                    User u = new User(x.getString("second"), classSessionInfo.getString("id"));
                    Pair<User, Pair<Integer,Integer>> studentInfo = new Pair<>(u, new Pair<Integer, Integer>(classSessionInfo.getInt("posRow"),classSessionInfo.getInt("posCol")));
                    r.add(studentInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        DomainControlFactory.getModelController().refreshStudentsInClassRecordView(r,dates,b);
    }

    public void getClassroomInfo(String classroomID) {
        ServicesFactory.getClassroomService().getClassroomInfo(classroomID);
    }

    public void updateClassroomDistributionClassInfo(JSONObject response, boolean b) {
        Classroom c = new Classroom();
        if (!b) {
            String classID = null;
            try {
                classID = response.getString("id");
                String name = response.getString("name");
                int rows = response.getInt("numRows");
                int cols = response.getInt("numCols");
                String schoolID = response.getString("schoolID");
                c = new Classroom(classID,name,rows,cols,schoolID);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        DomainControlFactory.getModelController().refreshClassroomDistributionClassInfo(c,b);
    }

    public void getClassroomsOfSchool(String schoolID) {
        ServicesFactory.getClassroomService().getClassroomsBySchoolID(schoolID);
    }




    public void SetClassroomsBySchoolIDResponse(boolean error, JSONArray response) {
        System.out.println(response.toString());
        classroomsFromCurrentSchool = new ArrayList<>();
        if (!error) {
            try {
                for (int i = 0; i < response.length(); ++i) {
                    Classroom c = Classroom.fromJSONtoClassroom(response.getJSONObject(i));
                    c.print();
                    classroomsFromCurrentSchool.add(c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            DomainControlFactory.getModelController().SetClassroomsBySchoolIDResponse(error,classroomsFromCurrentSchool);
        }
    }
}
