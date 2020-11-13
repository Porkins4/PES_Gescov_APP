package com.example.gescov.DomainLayer.Services;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gescov.DomainLayer.Conection;
import com.example.gescov.Singletons.CurrentContext;
import com.example.gescov.ViewLayer.home.ContagionRequestResult;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ContagionServiceImplementor implements IContagionService {

    private Conection conection;
    private RequestQueue queue;
    private final String ContagionLink = "https://gescov.herokuapp.com/api/contagion";

    public ContagionServiceImplementor() {}

    @Override
    public void sendAnswers(List<Boolean> answers) {
        queue =  Volley.newRequestQueue(CurrentContext.getContext());
        String url = "https://gescov.herokuapp.com/api/tracingTest";
        try {
            JSONObject userAnswers = new JSONObject("{\n" +
                    "\"answers\" : [\n" +
                    "    \""+answers.get(0).toString()+"\",\n" +
                    "    \""+answers.get(1).toString()+"\",\n" +
                    "    \""+answers.get(2).toString()+"\",\n" +
                    "    \""+answers.get(3).toString()+"\",\n" +
                    "    \""+answers.get(4).toString()+"\"\n" +
                    "],\n" +
                    "\"contID\": \"5faeb44b7635de0ccea64469\"\n" +
                    "}");

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, userAnswers, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getContagionList(String userId, String schoolId) {
        conection = new Conection();
        System.out.println(schoolId + "----------------------");
        String response = null;
        try {
            response = conection.execute(ContagionLink+"/now?schoolID="+schoolId).get();
        } catch (ExecutionException  | InterruptedException e ){
            e.printStackTrace();
        }
        if (response == null) return "Error";//TODO
        return response;
    }


    public void notifyContagion(MutableLiveData<ContagionRequestResult> result) {
        queue =  Volley.newRequestQueue(CurrentContext.getContext());
        JSONObject contagion;
        try {
            contagion = new JSONObject(
                    "{\n" +
                            "\"infectedID\" :\"5fa9d4aee59d4c4c5d57151c\",\n" +
                            "\"inCon\" :\"true\"\n" +
                            "}");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, ContagionLink, contagion, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response );
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
    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result) {
        queue =  Volley.newRequestQueue(CurrentContext.getContext());
        String infectedID = "5fa9d4aee59d4c4c5d57151c";
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, ContagionLink+"?infectedID="+infectedID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ContagionRequestResult aux = new ContagionRequestResult();
                aux.setError(new Pair<String,Boolean> ("notifyRecovery",true));
                if (error.networkResponse == null) aux.setError(new Pair<String,Boolean> ("notifyRecovery",false));
                result.setValue(aux);
            }
        });
        queue.add(stringRequest);
    }
}
