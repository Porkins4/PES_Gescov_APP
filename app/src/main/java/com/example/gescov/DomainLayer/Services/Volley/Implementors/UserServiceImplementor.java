package com.example.gescov.DomainLayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.IUserService;
import com.example.gescov.DomainLayer.Services.Volley.VolleyServices;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;

import org.json.JSONObject;

public class UserServiceImplementor implements IUserService {

    private static final String GESCOV_USERS_URI = "https://gescov.herokuapp.com/api/users/";

    public UserServiceImplementor() {}

    @Override
    public void changeUserProfile(String userId, String profile) {
        StringRequest request = new StringRequest(
                Request.Method.PUT, GESCOV_USERS_URI + userId + "/" + profile,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        DomainControlFactory.getUserModelController().setUserType(profile);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("something went wrong");
                    }
                }
        );
        VolleyServices.getRequestQueue().add(request);
    }

    @Override
    public void getUserID(String token) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GESCOV_USERS_URI+token,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String userID) {
                        DomainControlFactory.getUserModelController().setUserID(false,userID);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DomainControlFactory.getUserModelController().setUserID(true,null);
            }
        });
        VolleyServices.getRequestQueue().add(stringRequest);
    }

    @Override
    public void getUserInfo(String id) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, GESCOV_USERS_URI+id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        DomainControlFactory.getUserModelController().setLoggedInUser(false,response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DomainControlFactory.getUserModelController().setLoggedInUser(false, null);
            }
        });

        VolleyServices.getRequestQueue().add(jsonObjectRequest);
    }
}
