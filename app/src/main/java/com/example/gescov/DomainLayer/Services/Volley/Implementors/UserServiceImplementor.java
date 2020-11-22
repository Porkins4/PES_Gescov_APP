package com.example.gescov.DomainLayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gescov.DomainLayer.DomainLayerSingletons.DomainControlFactory;
import com.example.gescov.DomainLayer.DomainLayerSingletons.VolleyServices;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.IUserService;

public class UserServiceImplementor implements IUserService {

    private static String GESCOV_USERS_URI = "https://gescov.herokuapp.com/api/users/";

    public UserServiceImplementor() {}

    @Override
    public void changeUserProfile(String userId, String profile) {
        System.out.println(GESCOV_USERS_URI + userId + "/{profile}?profile=" + profile);
        StringRequest request = new StringRequest(
                Request.Method.PUT, GESCOV_USERS_URI + userId + "/{profile}?profile=" + profile,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        DomainControlFactory.getUserController().setUserType(profile);
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
