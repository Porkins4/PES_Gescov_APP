package com.example.gescov.domainlayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.ISubjectsService;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;


public class SubjectsServiceImplementor implements ISubjectsService {
    private static final String  SCHOOL_SUBJECTS = "https://gescov.herokuapp.com/api/subjects/schools/";
    private static final String SUBJECTS_URI = "https://gescov.herokuapp.com/api/subjects/";
    private static final String  CLASS_SESSION_BY_SUBJECT = "https://gescov.herokuapp.com/api/classSessions/subject/";


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
    public void assignUserToSubject(String subjectID, String userID, int activityIdentifier) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, SUBJECTS_URI + subjectID + "?userID=" +userID,
                response -> {
                    DomainControlFactory.getUserModelController().setSubjectID(subjectID,false, activityIdentifier);
                },
                error -> {
                    DomainControlFactory.getUserModelController().setSubjectID(subjectID,true, activityIdentifier);
                });
        VolleyServices.getRequestQueue().add(stringRequest);
    }

    @Override
    public void getClassSessions(String subjectID) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, CLASS_SESSION_BY_SUBJECT + subjectID, null,
                response -> DomainControlFactory.getClassSessionsModelController().setClassSessionsResult(false,response),
                error -> DomainControlFactory.getClassSessionsModelController().setClassSessionsResult(true,null)
                );
        VolleyServices.getRequestQueue().add(jsonArrayRequest);
    }

    @Override
    public void getSubjectsFromUser(String userID) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, SUBJECTS_URI + "user/" + userID, null,
                response -> DomainControlFactory.getSubjectModelController().setSubjectsFromUserResult(false,response),
                error -> DomainControlFactory.getSubjectModelController().setSubjectsFromUserResult(true,null)
        );
        VolleyServices.getRequestQueue().add(jsonArrayRequest);
    }


}
