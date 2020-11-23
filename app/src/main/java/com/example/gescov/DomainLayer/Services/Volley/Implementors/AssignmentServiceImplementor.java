package com.example.gescov.DomainLayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.IAssignmentService;
import com.example.gescov.DomainLayer.Services.Volley.VolleyServices;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;

import org.json.JSONArray;

public class AssignmentServiceImplementor implements IAssignmentService {

    private static final String GESCOV_ASSIGNMENTS_URI = "https://gescov.herokuapp.com/api/assignments/";

    public AssignmentServiceImplementor () {}

    @Override
    public void getAssignmentsForClassSession(String classroomID, String date, String hour) {//cuando se pueda escoger la class session: classDateHour?classroomID=5fbad920572f3d4f08fa36bb&date=03-02-2020&hour=15%3A00
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, GESCOV_ASSIGNMENTS_URI + "classroom/" + classroomID,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        DomainControlFactory.getAssignmentModelController().updateStudentsInClassSession(response,false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DomainControlFactory.getAssignmentModelController().updateStudentsInClassSession(null,true);
                    }
                }
        );
        RequestQueue q = VolleyServices.getRequestQueue();
        q.add(request);
    }
}
