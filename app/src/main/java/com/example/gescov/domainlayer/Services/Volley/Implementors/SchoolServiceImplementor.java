package com.example.gescov.domainlayer.Services.Volley.Implementors;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gescov.domainlayer.Services.Conection;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.ISchoolService;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolRequestResult;
import com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.viewlayer.SignUpAndLogin.TokenVerificationResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SchoolServiceImplementor implements ISchoolService {
    private Conection conection;
    private final String GET_CLASSROOM_DIMENSIONS_URI = "https://gescov.herokuapp.com/api/classrooms/distribution";
    private final String GET_CLASSROOM_STUDENTS_URI = "https://gescov.herokuapp.com/api/assignments/classroom";
    private final String POST_ASSIGNMENT_URI = "https://gescov.herokuapp.com/api/assignments";
    private final String POST_CREATE_SCHOOL_URI = "https://gescov.herokuapp.com/api/schools";
    private final String POST_CREATE_CLASSROOM_URI = "https://gescov.herokuapp.com/api/classrooms";
    private final String GET_STUDENTS_IN_CLASS_SESSION = "https://gescov.herokuapp.com/api/assignments/classroom";
    private final String GET_CHECK_LOGIN = "https://gescov.herokuapp.com/api/users/";
    private final String PUT_USER_TO_SCHOOL = "https://gescov.herokuapp.com/api/users/school/";
    private final String  SCHOOL_PUNTUATIONS = "https://gescov.herokuapp.com/api/schools/scores";

    private final String GESCOV_SCHOOLS_URI = "https://gescov.herokuapp.com/api/schools/";
    
    public SchoolServiceImplementor() {
        //Empty constructor
    }

    @Override
    public String getDimensions(String schoolId, String classroomId) {
        conection = new Conection();
        String response = null;
        try {
            response = conection.execute(GET_CLASSROOM_DIMENSIONS_URI+"?id=5f9c4a749213971828f1ace8").get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if (response == null) return "Failure at getting dimensions from the server";
        return response;
    }

    @Override
    public String getStudentsInClassroom(String classroom) {
        conection = new Conection();
        String response = null;
        try {
            response = conection.execute(GET_CLASSROOM_STUDENTS_URI+"?nameCen=A5S101").get();
        } catch (ExecutionException  | InterruptedException e ) {
            e.printStackTrace();
        }
        if (response == null) return "Failure at getting students in the specified classroom";
        return response;
    }
  @Override
    public String getAllSchools() {
        //call back-end Service
        conection = new Conection();
        String response = null;
        // hay que poner el identificador del usuario y el centro
        try {
            response = conection.execute("https://gescov.herokuapp.com/api/school").get();
        } catch (ExecutionException | InterruptedException e ){
            e.printStackTrace();
        }
        if (response == null) return "Something went wrong";//TODO
        return response;
    }

    @Override
    public void sendReservationRequest(String studentID, String classSessionID, int row, int col) {
        try {
            JSONObject postData = new JSONObject();
            postData.put("classSessionID",classSessionID);
            postData.put("id","");
            postData.put("posCol",col);
            postData.put("posRow",row);
            postData.put("studentID",studentID);

            RequestQueue requestQueue = Volley.newRequestQueue(VolleyServices.getCtx());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, POST_ASSIGNMENT_URI, postData,
                    response -> {

                    }, error -> {
                        if (error.networkResponse != null  && error.networkResponse.statusCode == 400  ) {
                            System.out.println("something went wrong :(");
                        }
                    });

            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            System.out.println("Error while creating data for the reservation");
        }
    }

    @Override
    public void createSchoolRequest(String schoolName, String schoolAddress, String schoolTelephone, String schoolWebsite, List<String> administratorsList, String creatorID) {

        try {
            JSONObject school = new JSONObject();
            school.put("address", schoolAddress);
            school.put("name", schoolName);
            school.put("phone", schoolTelephone);
            school.put("website", schoolWebsite);
            school.put("creatorID", creatorID);

            RequestQueue requestQueue = Volley.newRequestQueue(VolleyServices.getCtx());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, POST_CREATE_SCHOOL_URI, school,
                    response -> DomainControlFactory.getSchoolsModelCrontroller().refreshSchoolList(), error -> {
                        if (error.networkResponse != null  && error.networkResponse.statusCode == 400  ) {
                            System.out.println("something went wrong :(");
                            for (String header : error.networkResponse.headers.values()) {
                                System.out.println(header);
                            }
                        }
                    });

            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            System.out.println("Error while creating data for the reservation");
        }

    }

    @Override
    public void createClassroomRequest(String schoolID,  String classroomName, int classroomRows, int classroomCols) {
        JSONObject classroom = new JSONObject();
        try {
            classroom.put("name", classroomName);
            classroom.put("capacity", classroomRows*classroomCols);
            classroom.put("numRows", classroomRows);
            classroom.put("numCols", classroomCols);
            classroom.put("schoolID", schoolID);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue requestQueue = Volley.newRequestQueue(VolleyServices.getCtx());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, POST_CREATE_CLASSROOM_URI, classroom,
                response -> DomainControlFactory.getUserModelController().refreshSchoolClassrooms(DomainControlFactory.getSchoolsModelCrontroller().getCurrentSchool().getName()), error -> {
                    if (error.networkResponse != null  && error.networkResponse.statusCode == 400  ) {
                        System.out.println("something went wrong :(");
                    }
                });

        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult) {
        Response.Listener<JSONArray> listener = response -> {
            StudentsInClassSessionResult result = new StudentsInClassSessionResult();
            try {
                List<String> studentsList = new ArrayList<>();
                for (int i = 0; i < response.length(); ++i) {
                    JSONObject assignment = response.getJSONObject(i);
                    JSONObject student = new JSONObject(assignment.getString("student"));
                    studentsList.add(student.getString("name"));
                }
                result.setStudentNames(studentsList);
                result.setError(false);
                studentsResult.setValue(result);
            } catch (JSONException e) {
                    result.setError(true);
                    studentsResult.setValue(result);
                    System.out.println("error while trying to transform JSON results");
            }
        };

        Response.ErrorListener errorListener = error -> {
            StudentsInClassSessionResult result = new StudentsInClassSessionResult();
            result.setError(true);
            studentsResult.setValue(result);
            System.out.println("something went wrong");
        };
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,GET_STUDENTS_IN_CLASS_SESSION + "?name=A5S201",null,listener,errorListener);
        VolleyServices.addJSONArrayRequest(request);
    }


    @Override
    public void checkUserLogin(MutableLiveData<TokenVerificationResult> r) {
        RequestQueue requestQueue = Volley.newRequestQueue(VolleyServices.getCtx());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_CHECK_LOGIN+(r.getValue()).getUserToken(),
                response -> {
                    TokenVerificationResult result = new TokenVerificationResult(true, response);
                    r.setValue(result);
                }, error -> {
                    TokenVerificationResult result = new TokenVerificationResult(false, null);
                    r.setValue(result);
                });
        requestQueue.add(stringRequest);
    }

    @Override
    public void refreshUser(String id) {
        RequestQueue requestQueue = Volley.newRequestQueue(VolleyServices.getCtx());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, GET_CHECK_LOGIN+id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        DomainControlFactory.getUserModelController().refreshLoggedUser(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void addStudentToCenter(String id, String schoolId, MutableLiveData<SchoolRequestResult> result) {
        RequestQueue requestQueue = Volley.newRequestQueue(VolleyServices.getCtx());
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, PUT_USER_TO_SCHOOL+id+"?schoolID="+schoolId,
                response -> {
                    SchoolRequestResult aux = new SchoolRequestResult();
                    aux.setError(false);
                    result.setValue(aux);
                    DomainControlFactory.getUserModelController().setNewSchoolID(schoolId);
                }, error -> {
                    SchoolRequestResult aux = new SchoolRequestResult();
                    aux.setError(true);
                    result.setValue(aux);
                });
        requestQueue.add(stringRequest);
    }

    @Override
    public void getNumContagionPerSchool(int from) {
        RequestQueue requestQueue = Volley.newRequestQueue(VolleyServices.getCtx());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET,SCHOOL_PUNTUATIONS , null,
                response -> {
                    try {
                        DomainControlFactory.getSchoolsModelCrontroller().sendResponseOfNumContagionPerSchool(response,from);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, error -> {

                });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void setGraph(String schoolId) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, GESCOV_SCHOOLS_URI+schoolId+"/tracking",null,
                response -> {
                    try {
                        DomainControlFactory.getSchoolsModelCrontroller().sendResponseOfGraph(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                }
        );
        VolleyServices.getRequestQueue().add(request);
    }
    //-----------------------------------------------------------------
    // used to update the information of all the schools belonging to the current user
    @Override
    public void getSchool(String schoolID) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, GESCOV_SCHOOLS_URI+"id/" + schoolID, null,
                response -> DomainControlFactory.getSchoolsModelCrontroller().updateIthUserSchool(response),
                error -> { });

        VolleyServices.getRequestQueue().add(jsonObjectRequest);
    }

    @Override
    public void getContactsFromCenter(String schoolID) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, GET_CHECK_LOGIN + "/school?schoolID=" + schoolID,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        DomainControlFactory.getUserModelController().setContactsFromSelectedCenter(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        VolleyServices.getRequestQueue().add(request);
    }
}