qqpackage com.example.gescov.domainlayer.Services.Volley.Implementors;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IUserService;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import org.json.JSONException;
import org.json.JSONObject;

public class UserServiceImplementor implements IUserService {

    private static final String GESCOV_USERS_URI = "https://gescov.herokuapp.com/api/users/";

    public UserServiceImplementor() {}

    @Override
    public void changeUserProfile(String userId, boolean isStudent) {
        Boolean metaIsStudent = isStudent;
        System.out.println(GESCOV_USERS_URI + userId + "/" + metaIsStudent.toString());
        StringRequest request = new StringRequest(
                Request.Method.PUT, GESCOV_USERS_URI + userId + "/" + metaIsStudent.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        DomainControlFactory.getUserModelController().setUserType(metaIsStudent.toString());
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

    @Override
    public void setUserToken(String userID, String token) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, GESCOV_USERS_URI + userID + "/deviceToken",
                response -> {
                    System.out.println("tot ok");
                }, error -> {
            if (error.networkResponse != null  && error.networkResponse.statusCode == 400  ) {
                System.out.println("something went wrong :(");
            }
        }) {
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
                response -> {
                    System.out.println("toKEN BORRADO");
                }, error -> {
            if (error.networkResponse != null  && error.networkResponse.statusCode == 400  ) {
                System.out.println("something went wrong :(");
            }
        }) {
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
