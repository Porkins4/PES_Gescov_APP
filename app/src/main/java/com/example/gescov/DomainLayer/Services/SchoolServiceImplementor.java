package com.example.gescov.DomainLayer.Services;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gescov.DomainLayer.Conection;
import com.example.gescov.Singletons.CurrentContext;
import com.example.gescov.Singletons.VolleyServices;
import com.example.gescov.ViewLayer.StudentsInClassSession.StudentsInClassSessionResult;
import com.example.gescov.ViewLayer.MainView.TokenVerificationResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SchoolServiceImplementor implements ISchoolService {
    private Conection conection;
    private final String GET_CLASSROOM_DIMENSIONS_URI = "https://gescov.herokuapp.com/api/classroom/distribution";
    private final String GET_CLASSROOM_STUDENTS_URI = "https://gescov.herokuapp.com/api/assignment/classroom";
    private final String POST_ASSIGNMENT_URI = "https://gescov.herokuapp.com/api/assignment";
    private final String POST_CREATE_SCHOOL_URI = "https://gescov.herokuapp.com/api/school";
    private final String POST_CREATE_CLASSROOM_URI = "https://gescov.herokuapp.com/api/classroom";
    private final String GET_STUDENTS_IN_CLASS_SESSION = "https://gescov.herokuapp.com/api/assignment/classroom";
    private final String GET_CHECK_LOGIN = "https://gescov.herokuapp.com/api/user/";

    public SchoolServiceImplementor() { }

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
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
        if (response == null) return "Hay fallo";//TODO
        return response;
    }

    @Override
    public void sendReservationRequest(String name, String aula, int row, int col) {
        try {
            JSONObject postData = new JSONObject(
                    "{\n" +
                            "    \"posCol\":"+ col + ",\n" +
                            "    \"posRow\":" + row + ",\n" +
                            "    \"student\":{\n" +
                            "    \"name\": \"" + name + "\",\n" +
                            "    \"schools\": [\n" +
                            "        {\n" +
                            "            \"address\": \"Carrer de Pau Gargallo, 14\",\n" +
                            "            \"name\": \"FME\",\n" +
                            "            \"state\": \"open\",\n" +
                            "            \"creator\": \"Xicu Torres\"\n" +
                            "        } \n" +
                            "    ]\n" +
                            "},\n" +
                            "    \"classSession\":{\n" +
                            "    \"classroom\":{\n" +
                            "        \"name\": \"A5S201\",\n" +
                            "        \"capacity\": \"25\",\n" +
                            "        \"numRows\":\"5\",\n" +
                            "        \"numCols\":\"10\",\n" +
                            "        \"school\":{\n" +
                            "            \"address\": \"Carrer de Pau Gargallo, 14\",\n" +
                            "            \"name\": \"FME\",\n" +
                            "            \"state\": \"open\",\n" +
                            "            \"creator\": \"Xicu Torres\"\n" +
                            "        }\n" +
                            "    },\n" +
                            "    \"subject\":{\n" +
                            "        \"name\": \"AC\",\n" +
                            "        \"school\":{\n" +
                            "            \"address\": \"Carrer de Pau Gargallo, 14\",\n" +
                            "            \"name\": \"FME\",\n" +
                            "             \"state\": \"open\",\n" +
                            "             \"creator\": \"Xicu Torres\"\n" +
                            "        }\n" +
                            "    },\n" +
                            "    \"teacher\":{\n" +
                            "             \"name\": \"Ponç Partido Perdido\",\n" +
                            "             \"schools\": [{\n" +
                            "                     \"address\": \"Carrer de Pau Gargallo, 14\",\n" +
                            "                     \"name\": \"FME\",\n" +
                            "                     \"state\": \"open\",\n" +
                            "                     \"creator\": \"Xicu Torres\"\n" +
                            "               }]\n" +
                            "    },\n" +
                            "    \"hour\": \"12:00:00\",\n" +
                            "    \"date\": \"03-02-2020\"\n" +
                            "}\n" +
                            "}"
            );

            RequestQueue requestQueue = Volley.newRequestQueue(CurrentContext.getContext());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, POST_ASSIGNMENT_URI, postData,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error.networkResponse != null  && error.networkResponse.statusCode == 400  ) {
                        System.out.println("something went wrong :(");
                    }
                }
            });

            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            System.out.println("Error while creating data for the reservation");
        }
    }

    @Override
    public void createSchoolRequest(String schoolName, String schoolAddress, String creator) {
        System.out.println(schoolName);
        System.out.println(schoolAddress);
        System.out.println(creator);
        try {
            JSONObject school = new JSONObject("{\n" +
                    "    \"address\": \""+schoolAddress+"\",\n" +
                    "    \"name\": \""+schoolName+"\",\n" +
                    "    \"state\": \"open\",\n" +
                    "    \"creator\": \""+creator+"\",\n" +
                    "    \"administrators\": [\n" +
                    "        \""+creator+"\"\n" +
                    "    ]\n" +
                    "}"
            );

            RequestQueue requestQueue = Volley.newRequestQueue(CurrentContext.getContext());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, POST_CREATE_SCHOOL_URI, school,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error.networkResponse != null  && error.networkResponse.statusCode == 400  ) {
                        System.out.println("something went wrong :(");
                    }
                }
            });

            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            System.out.println("Error while creating data for the reservation");
        }

    }

    @Override
    public void createClassroomRequest(String schoolName, String schoolAdress, String schoolState, float schoolLatitude, float schoolLongitude, String schoolCreator, String classroomName, String classrooomCapacity, String classroomRows, String classroomCols) {
        JSONObject classroom = new JSONObject();
        try {
            classroom.put("name", classroomName);
            classroom.put("capacity", classrooomCapacity);
            classroom.put("numRows", classroomRows);
            classroom.put("numCols", classroomCols);

            JSONObject school = new JSONObject();
            school.put("name", schoolName);
            school.put("address", schoolAdress);
            school.put("state", schoolState);
            school.put("latitude", schoolLatitude);
            school.put("longitude", schoolLongitude);
            school.put("creator", schoolCreator);

            classroom.put("school", school);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue requestQueue = Volley.newRequestQueue(CurrentContext.getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, POST_CREATE_CLASSROOM_URI, classroom,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null  && error.networkResponse.statusCode == 400  ) {
                    System.out.println("something went wrong :(");
                }
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void getStudentsInClassSession(MutableLiveData<StudentsInClassSessionResult> studentsResult) {
        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
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
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                StudentsInClassSessionResult result = new StudentsInClassSessionResult();
                result.setError(true);
                studentsResult.setValue(result);
                System.out.println("something went wrong");
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,GET_STUDENTS_IN_CLASS_SESSION + "?name=A5S201",null,listener,errorListener);
        VolleyServices.addJSONArrayRequest(request);
    }


    @Override
    public void checkUserLogin(MutableLiveData<TokenVerificationResult> r) {
        RequestQueue requestQueue = Volley.newRequestQueue(VolleyServices.getCtx());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_CHECK_LOGIN+(r.getValue()).getUserToken(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        TokenVerificationResult result = new TokenVerificationResult(true, response);
                        r.setValue(result);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TokenVerificationResult result = new TokenVerificationResult(false, null);
                r.setValue(result);
            }
        });
        requestQueue.add(stringRequest);
    }

}
