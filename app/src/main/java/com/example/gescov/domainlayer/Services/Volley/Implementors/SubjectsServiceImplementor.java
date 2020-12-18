package com.example.gescov.domainlayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.ISubjectsService;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;



public class SubjectsServiceImplementor implements ISubjectsService {
    private static final String  SCHOOL_SUBJECTS = "https://gescov.herokuapp.com/api/subjects/schools/";


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


}
