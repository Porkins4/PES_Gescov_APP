package com.example.gescov.domainlayer.Services.Volley.Implementors;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

    private Conection conection;
    private RequestQueue queue;
    private final String ContagionLink = "https://gescov.herokuapp.com/api/contagions";
    private final String TracingTestLink = "https://gescov.herokuapp.com/api/tracingTests";

    public ContagionServiceImplementor() {}

    @Override
    public void sendAnswers(List<Boolean> answers, String idContagion) {
        queue =  Volley.newRequestQueue(VolleyServices.getCtx());
        try {
            JSONArray jsonUserAnswers = new JSONArray(answers);
            JSONObject userAnswers = new JSONObject();
            userAnswers.put("answers",jsonUserAnswers);
            userAnswers.put("contID",idContagion);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST,TracingTestLink, userAnswers, response -> {
                    }, error -> {

                    });
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void notifyPossibleContagion(String id, MutableLiveData<ContagionRequestResult> result) {
        queue =  Volley.newRequestQueue(VolleyServices.getCtx());
        JSONObject contagion;
        try {
            contagion = new JSONObject();
            contagion.put("infectedID",id);
            contagion.put("inCon",false);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, ContagionLink, contagion, response -> {
                        try {
                            String idContagion = response.getString("id");
                            ContagionRequestResult aux = new ContagionRequestResult();
                            aux.setError(new Pair<String,Boolean>("notifyPossiblePositive",false));
                            DomainControlFactory.getUserModelController().updateContagionID(idContagion);
                            result.setValue(aux);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                        // TODO: Handle error
                        ContagionRequestResult aux = new ContagionRequestResult();
                        aux.setError(new Pair<String,Boolean> ("notifyPossiblePositive",true));
                        if(error.networkResponse == null ) aux.setError(new Pair<String,Boolean> ("notifyPositive",false));
                        result.setValue(aux);
                    });
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getContagionList(String userId, String schoolId) {
        conection = new Conection();
        String response = null;
        try {
            response = conection.execute(ContagionLink+"/now/"+schoolId).get();
        } catch (ExecutionException  | InterruptedException e ){
            e.printStackTrace();
        }
        if (response == null) return "Error";//TODO
        return response;
    }


    public void notifyContagion(MutableLiveData<ContagionRequestResult> result,String ConfirmedInfected, String id) {
        queue =  Volley.newRequestQueue(VolleyServices.getCtx());
        JSONObject contagion;
        try {
            contagion = new JSONObject();
            contagion.put("infectedID",id);
            contagion.put("inCon",ConfirmedInfected);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, ContagionLink, contagion, response -> {
                        try {
                            String idContagion = response.getString("id");
                            ContagionRequestResult aux = new ContagionRequestResult();
                            aux.setError(new Pair<String,Boolean>("notifyPositive",false));
                            DomainControlFactory.getUserModelController().updateContagionID(idContagion);
                            result.setValue(aux);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            ContagionRequestResult aux = new ContagionRequestResult();
                            aux.setError(new Pair<String,Boolean> ("notifyPositive",true));
                            if(error.networkResponse == null ) aux.setError(new Pair<String,Boolean> ("notifyPositive",false));
                            result.setValue(aux);
                        }
                    });
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result,String id) {
        queue =  Volley.newRequestQueue(VolleyServices.getCtx());
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, ContagionLink+"/"+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ContagionRequestResult aux = new ContagionRequestResult();
                        aux.setError(new Pair<String,Boolean> ("notifyRecovery",false));
                        DomainControlFactory.getUserModelController().updateContagionID(null);
                        result.setValue(aux);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ContagionRequestResult aux = new ContagionRequestResult();
                aux.setError(new Pair<String,Boolean> ("notifyRecovery",true));
                result.setValue(aux);
            }
        });
        queue.add(stringRequest);
    }

    //-------------------------------------------------------------
    //limpio
    @Override
    public void getContagionID(String id) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, ContagionLink + "/student/"+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String contagionID) {
                        DomainControlFactory.getUserModelController().setContagionId(contagionID, false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DomainControlFactory.getUserModelController().setContagionId(null, false);
            }
        });
        VolleyServices.getRequestQueue().add(stringRequest);
    }
}
