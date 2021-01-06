package com.example.gescov.domainlayer.Services.Volley.Implementors;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.gescov.domainlayer.Services.Conection;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IContagionService;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.viewlayer.home.ContagionRequestResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ContagionServiceImplementor implements IContagionService {


    private static final String CONTAGION_LINK = "https://gescov.herokuapp.com/api/contagions";
    private static final String TRACING_TEST_LINK = "https://gescov.herokuapp.com/api/tracingTests";


    @Override
    public void sendAnswers(List<Boolean> answers, String idContagion) {
        try {
            JSONArray jsonUserAnswers = new JSONArray(answers);
            JSONObject userAnswers = new JSONObject();
            userAnswers.put("answers",jsonUserAnswers);
            userAnswers.put("contID",idContagion);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, TRACING_TEST_LINK, userAnswers, response -> {
                    }, error -> {

                    });
            VolleyServices.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyPossibleContagion(String id, MutableLiveData<ContagionRequestResult> result) {
        JSONObject contagion;
        String notifyPossiblePositivePositive = "notifyPossiblePositive";
        try {
            contagion = new JSONObject();
            contagion.put("infectedID",id);
            contagion.put("inCon",false);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, CONTAGION_LINK, contagion, response -> {
                        try {
                            String idContagion = response.getString("id");
                            ContagionRequestResult aux = new ContagionRequestResult();
                            aux.setError(new Pair<>(notifyPossiblePositivePositive, false));
                            DomainControlFactory.getUserModelController().updateContagionID(idContagion);
                            result.setValue(aux);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                        // TODO: Handle error
                        ContagionRequestResult aux = new ContagionRequestResult();
                        aux.setError(new Pair<>(notifyPossiblePositivePositive, true));
                        if(error.networkResponse == null ) aux.setError(new Pair<> (notifyPossiblePositivePositive,false));
                        result.setValue(aux);
                    });
            VolleyServices.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getContagionList(String schoolId) {
        Conection conection = new Conection();
        String response = null;
        try {
            response = conection.execute(CONTAGION_LINK +"/now/"+schoolId).get();
        } catch (ExecutionException  | InterruptedException e ){
            e.printStackTrace();
        }
        if (response == null) return "Error";//TODO
        return response;
    }


    public void notifyContagion(MutableLiveData<ContagionRequestResult> result,String confirmedInfected, String id) {
        JSONObject contagion;
        String notifyPositive = "notifyPositive";
        try {
            contagion = new JSONObject();
            contagion.put("infectedID",id);
            contagion.put("inCon",confirmedInfected);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, CONTAGION_LINK, contagion, response -> {
                        try {
                            String idContagion = response.getString("id");
                            ContagionRequestResult aux = new ContagionRequestResult();
                            aux.setError(new Pair<>(notifyPositive, false));
                            DomainControlFactory.getUserModelController().updateContagionID(idContagion);
                            result.setValue(aux);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                        // TODO: Handle error
                        ContagionRequestResult aux = new ContagionRequestResult();
                        aux.setError(new Pair<>(notifyPositive, true));
                        if(error.networkResponse == null ) aux.setError(new Pair<>(notifyPositive, false));
                        result.setValue(aux);
                    });
            VolleyServices.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result,String id) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, CONTAGION_LINK +"/"+id,
                response -> {
                    ContagionRequestResult aux = new ContagionRequestResult();
                    aux.setError(new Pair<>("notifyRecovery", false));
                    DomainControlFactory.getUserModelController().updateContagionID(null);
                    result.setValue(aux);
                }, error -> {
                    ContagionRequestResult aux = new ContagionRequestResult();
                    aux.setError(new Pair<>("notifyRecovery", true));
                    result.setValue(aux);
                });
        VolleyServices.getRequestQueue().add(stringRequest);
    }


    @Override
    public void getContagionID(String id) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, CONTAGION_LINK + "/student/"+id,
                contagionID -> DomainControlFactory.getUserModelController().setContagionId(contagionID, false), error -> DomainControlFactory.getUserModelController().setContagionId(null, false));
        VolleyServices.getRequestQueue().add(stringRequest);
    }

    @Override
    public void getTestResults(String userID) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, TRACING_TEST_LINK +"?userID="+userID,null,
                response -> DomainControlFactory.getTracingTestResultModel().sendTestAnswers(response), error -> {

                });
        VolleyServices.getRequestQueue().add(jsonArrayRequest);

    }
}
