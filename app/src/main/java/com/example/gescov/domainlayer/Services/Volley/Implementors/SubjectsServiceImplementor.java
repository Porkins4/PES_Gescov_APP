package com.example.gescov.domainlayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.ISubjectsService;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;


public class SubjectsServiceImplementor implements ISubjectsService {
    private static final String  SCHOOL_SUBJECTS = "https://gescov.herokuapp.com/api/subjects/schools/";
    private static final String ADD_STUDENT_SUBJECT = "https://gescov.herokuapp.com/api/subjects/new/";


    @Override
    public void getSubjects(String schooldID) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, SCHOOL_SUBJECTS  + schooldID,null,
                response -> DomainControlFactory.getSubjectModelController().sendResponseOfSubjects(response),
                error -> {

                }
        );
        VolleyServices.getRequestQueue().add(request);
    }

    @Override
    public void assignStudent(String subjectID, String userID) {
        //{specific}?id=5fd7a37db762197df050d295&userId=5fcac8a2349ec432db90b7b3
        RequestQueue requestQueue = Volley.newRequestQueue(VolleyServices.getCtx());

        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, ADD_STUDENT_SUBJECT + "{specific}?id="+ subjectID+"&userId="+userID,
                response -> {
                    DomainControlFactory.getUserModelController().setSubjectID(subjectID,false);
                },
                error -> {
                    DomainControlFactory.getUserModelController().setSubjectID(subjectID,true);
                });

        requestQueue.add(stringRequest);

    }


}
