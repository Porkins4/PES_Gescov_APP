package com.example.gescov.DomainLayer.Controllers;

import com.example.gescov.DomainLayer.Classmodels.Assignment;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;
import com.example.gescov.DomainLayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AssignmentsModelController {

    public void getAssignmentsForClassSession(String classroomID, String date, String hour) {
        ServicesFactory.getAssignmentService().getAssignmentsForClassSession(classroomID,date,hour);
    }

    public void updateStudentsInClassSession(JSONArray response, boolean b) {
        List<Assignment> r = new ArrayList<>();
        if (!b) {
            try {
            for (int i = 0; i < response.length(); ++i) {
                JSONObject currentAssignment = response.getJSONObject(i);
                JSONObject currentAssignmentInfo = new JSONObject(currentAssignment.getString("first"));
                String name = currentAssignment.getString("second");
                String studentID = currentAssignmentInfo.getString("id");
                int row = currentAssignmentInfo.getInt("posRow");
                int col = currentAssignmentInfo.getInt("posCol");
                Assignment na = new Assignment(name,studentID,row,col);
                r.add(na);
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        DomainControlFactory.getModelController().refreshClassroomDistributionAssignments(r,b);
    }
}
