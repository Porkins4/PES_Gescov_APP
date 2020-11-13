package com.example.gescov.DomainLayer.Services;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gescov.DomainLayer.Conection;
import com.example.gescov.Singletons.CurrentContext;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ContagionServiceImplementor implements IContagionService {

    private Conection conection;
    private RequestQueue queue;
    private Boolean success;
    public ContagionServiceImplementor() {
    }


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
                    "\"contID\": \"5fadc098aefa495c4e20f264\"\n" +
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
        //call back-end Service
        conection = new Conection();
        String response = null;
       // hay que poner el identificador del usuario y el centro
        try {
            response = conection.execute("https://gescov.herokuapp.com/api/contagion/now?nameCen="+schoolId).get();
        } catch (ExecutionException  | InterruptedException e ){
            e.printStackTrace();
        }
        if (response == null) return "Hay fallo";//TODO
        return response;
    }
    public Boolean notifyContagion() {
        success = true;
        queue =  Volley.newRequestQueue(CurrentContext.getContext());
        JSONObject contagion;
        try {
            contagion = new JSONObject(
                    "{\n" +
                    "    \"infected\":{\n" +
                    "        \"name\": \"Marc\",\n" +
                    "        \"schools\": [\n" +
                    "            {\n" +
                    "                \"address\": \"Carrer Jordi Girona, 1-3\",\n" +
                    "                \"name\": \"FIB\",\n" +
                    "               \"creator\": \"Xicu Torres\"\n" +
                    "            } \n" +
                    "        ]\n" +
                    "    }\n" +
                    "}");
            String url = "https://gescov.herokuapp.com/api/contagion";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, contagion, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {

                            // TODO: Handle error
                            if (error.networkResponse != null && error.networkResponse.statusCode == 400 ) {
                                success = false;
                            }
                        }
                    });
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(success+" el success ");
        return success;
    }
}
