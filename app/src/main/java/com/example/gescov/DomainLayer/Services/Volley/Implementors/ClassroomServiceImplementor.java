package com.example.gescov.DomainLayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.gescov.DomainLayer.DomainLayerSingletons.DomainControlFactory;
import com.example.gescov.DomainLayer.DomainLayerSingletons.VolleyServices;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.IClassroomService;

import org.json.JSONArray;

public class ClassroomServiceImplementor implements IClassroomService {

    private static String GESCOV_ASSIGNMENTS_URI = "https://gescov.herokuapp.com/api/assignments/";

    public ClassroomServiceImplementor(){}
    @Override
    public void getStudentsInClassRecord(String classroomId, String date) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, GESCOV_ASSIGNMENTS_URI + "classroom/" + classroomId,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        DomainControlFactory.getClassroomModelController().updateStudentsInClassRecordView(response,false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("something went wrong");
                    }
                }
        );
        RequestQueue q = VolleyServices.getRequestQueue();
        q.add(request);
    }
}
