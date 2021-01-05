package com.example.gescov.domainlayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IClassroomService;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClassroomServiceImplementor implements IClassroomService {

    private static final String GESCOV_ASSIGNMENTS_URI = "https://gescov.herokuapp.com/api/assignments/";
    private static final String GESCOV_CLASSROOMS_URI = "https://gescov.herokuapp.com/api/classrooms/";

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
                        DomainControlFactory.getClassroomModelController().updateStudentsInClassRecordView(null,true);
                    }
                }
        );
        VolleyServices.getRequestQueue().add(request);
    }

    @Override
    public void getClassroomInfo(String classroomId) {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, GESCOV_CLASSROOMS_URI + classroomId,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        DomainControlFactory.getClassroomModelController().updateClassroomDistributionClassInfo(response,false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DomainControlFactory.getClassroomModelController().updateClassroomDistributionClassInfo(null,true);
                    }
                }
        );
        RequestQueue q = VolleyServices.getRequestQueue();
        q.add(request);
    }

    //Request.Method.POST, GESCOV_CLASSROOMS_URI + classID+"/",classSchedule,

    @Override
    public void setSchedule( JSONArray classSchedule,String classID) {

        for (int i = 0; i < classSchedule.length(); ++i) {
            JSONObject aux = null;
            try {
                aux = classSchedule.getJSONObject(i);
                System.out.println(aux);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.POST, GESCOV_CLASSROOMS_URI + classID+"/schedule",classSchedule,
                response -> {
                },
                error -> {
                }
        );
        RequestQueue q = VolleyServices.getRequestQueue();
        q.add(request);
    }
}
