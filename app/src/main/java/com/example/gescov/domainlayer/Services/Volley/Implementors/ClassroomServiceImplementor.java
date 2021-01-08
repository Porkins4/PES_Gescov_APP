package com.example.gescov.domainlayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gescov.domainlayer.Classmodels.ClassSessionModel;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IClassroomService;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClassroomServiceImplementor implements IClassroomService {

    private static final String GESCOV_ASSIGNMENTS_URI = "https://gescov.herokuapp.com/api/assignments/";
    private static final String GESCOV_CLASSROOMS_URI = "https://gescov.herokuapp.com/api/classrooms/";
    private static final String GESCOV_CLASS_SESSION_URI = "https://gescov.herokuapp.com/api/classSessions/";

    public ClassroomServiceImplementor(){}

    @Override
    public void getStudentsInClassRecord(String classroomId) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, GESCOV_ASSIGNMENTS_URI + "classroom/" + classroomId,null,
                response -> DomainControlFactory.getClassroomModelController().updateStudentsInClassRecordView(response,false),
                error -> DomainControlFactory.getClassroomModelController().updateStudentsInClassRecordView(null,true)
        );
        VolleyServices.getRequestQueue().add(request);
    }

    @Override
    public void getClassroomInfo(String classroomId) {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, GESCOV_CLASSROOMS_URI + classroomId,null,
                response -> DomainControlFactory.getClassroomModelController().updateClassroomDistributionClassInfo(response,false),
                error -> DomainControlFactory.getClassroomModelController().updateClassroomDistributionClassInfo(null,true)
        );
        RequestQueue q = VolleyServices.getRequestQueue();
        q.add(request);
    }

    //Request.Method.POST, GESCOV_CLASSROOMS_URI + classID+"/",classSchedule,

    @Override
    public void setSchedule( JSONArray classSchedule,String classID) {

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.POST, GESCOV_CLASSROOMS_URI + classID+"/schedule",classSchedule,
                response -> {
                },
                error -> {
                }
        );
        RequestQueue q = VolleyServices.getRequestQueue();
        q.add(request);
    }

    @Override
    public void getClassroomsBySchoolID(String schoolID) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, GESCOV_CLASSROOMS_URI + "school/" + schoolID,null,
                response -> DomainControlFactory.getClassroomModelController().setClassroomsBySchoolIDResponse(false, response),
                error -> DomainControlFactory.getClassroomModelController().setClassroomsBySchoolIDResponse(true, null)
        );
        VolleyServices.getRequestQueue().add(request);
    }

    @Override
    public void getSchedule(String classID) {

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, GESCOV_CLASSROOMS_URI + classID+"/schedule",null,
                response -> {
                    DomainControlFactory.getScheduleModelController().sendResponseOfSchedule(response);
                },
                error -> {
                }
        );
        VolleyServices.getRequestQueue().add(request);



    }

    @Override
    public void createEvent(ClassSessionModel classSession) {
        JSONObject postData = new JSONObject();
        try {
            postData.put("classroomID", classSession.getClassroomID());
            postData.put("subjectID", classSession.getSubjectID());
            postData.put("teacherID", classSession.getTeacherID());
            postData.put("concept",classSession.getConcept());
            postData.put("hour", classSession.getHour());
            postData.put("finishHour", classSession.getFinishHour());
            postData.put("date",classSession.getDate());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //do not transform to lambda function (network response will not be recognized)
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, GESCOV_CLASS_SESSION_URI, postData,
                response -> DomainControlFactory.getClassSessionsModelController().notifyCreateEventResponse(false, 200, response),
                error -> DomainControlFactory.getClassSessionsModelController().notifyCreateEventResponse(true, error.networkResponse.statusCode, null)
        );
        VolleyServices.getRequestQueue().add(request);
    }
}
