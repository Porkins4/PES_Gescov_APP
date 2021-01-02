package com.example.gescov.domainlayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IEventService;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

public class EventServiceImplementor implements IEventService {
    private final String USERS_BY_SUBJECT_URI = "https://gescov.herokuapp.com/api/subjects/";

    @Override
    public void getGuests(String subjectID) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, USERS_BY_SUBJECT_URI  + subjectID+"/users",null,
                response -> DomainControlFactory.getEventModelController().sendResponseOfGuests(response),
                error -> {

                }
        );
        VolleyServices.getRequestQueue().add(request);
    }
}
