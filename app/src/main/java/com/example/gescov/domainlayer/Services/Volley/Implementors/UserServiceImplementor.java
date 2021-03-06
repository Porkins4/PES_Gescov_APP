package com.example.gescov.domainlayer.Services.Volley.Implementors;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IUserService;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

public class UserServiceImplementor implements IUserService {

    private static final String GESCOV_USERS_URI = "https://gescov.herokuapp.com/api/users/";

    public UserServiceImplementor() {}

    @Override
    public void changeUserProfile(String userId, boolean isStudent) {
        Boolean metaIsStudent = isStudent;
        StringRequest request = new StringRequest(
                Request.Method.PUT, GESCOV_USERS_URI + userId + "/" + metaIsStudent.toString(),
                response -> DomainControlFactory.getUserModelController().setUserType(metaIsStudent.toString()),
                error -> {
                }
        );
        VolleyServices.getRequestQueue().add(request);
    }

    @Override
    public void getUserID(String token) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GESCOV_USERS_URI+token,
                userID -> DomainControlFactory.getUserModelController().setUserID(false,userID),
                error -> DomainControlFactory.getUserModelController().setUserID(true,null));
        VolleyServices.getRequestQueue().add(stringRequest);
    }

    @Override
    public void getUserInfo(String id) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, GESCOV_USERS_URI+id, null,
                response -> DomainControlFactory.getUserModelController().setLoggedInUser(false,response),
                error -> DomainControlFactory.getUserModelController().setLoggedInUser(false, null));

        VolleyServices.getRequestQueue().add(jsonObjectRequest);
    }

    @Override
    public void setUserToken(String userID, String token) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, GESCOV_USERS_URI + userID + "/deviceToken",
                response -> { },
                error -> {}
        ) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return token.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        VolleyServices.getRequestQueue().add(stringRequest);
    }

    @Override
    public void deleteUserToken(String userID, String token) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, GESCOV_USERS_URI + userID + "/deviceToken",
                response -> { },
                error -> { }
        ) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return token.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        VolleyServices.getRequestQueueCustomDeleteStringRequest().add(stringRequest);
    }
}
