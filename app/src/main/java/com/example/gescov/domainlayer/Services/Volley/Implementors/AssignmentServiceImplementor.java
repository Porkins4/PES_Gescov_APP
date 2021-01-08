package com.example.gescov.domainlayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IAssignmentService;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

public class AssignmentServiceImplementor implements IAssignmentService {

    private static final String GESCOV_ASSIGNMENTS_URI = "https://gescov.herokuapp.com/api/assignments/";

    public AssignmentServiceImplementor () {}

    @Override
    public void getAssignmentsForClassSession(String classSessionID) {//cuando se pueda escoger la class session: classDateHour?classroomID=5fbad920572f3d4f08fa36bb&date=03-02-2020&hour=15%3A00
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, GESCOV_ASSIGNMENTS_URI + "classSession/" + classSessionID,null,
                response -> {
                    DomainControlFactory.getAssignmentModelController().updateStudentsInClassSession(response,false);
                },
                error -> DomainControlFactory.getAssignmentModelController().updateStudentsInClassSession(null,true)
        );
        RequestQueue q = VolleyServices.getRequestQueue();
        q.add(request);
    }
}
