package com.example.gescov.DomainLayer.Services;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gescov.DomainLayer.Conection;
import com.example.gescov.Singletons.CurrentContext;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

public class ContagionServiceImplementor implements IContagionService {

    private Conection conection;
    private RequestQueue queue;
    private Boolean success;
    private final String ContagionLink = "https://gescov.herokuapp.com/api/contagion";

    public ContagionServiceImplementor() {}

    @Override
    public String getContagionList(String userId, String schoolId) {
        conection = new Conection();
        String response = null;
        try {
            response = conection.execute(ContagionLink+"/now?nameCen="+schoolId).get();
        } catch (ExecutionException  | InterruptedException e ){
            e.printStackTrace();
        }
        if (response == null) return "Error";//TODO
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
                    "        \"name\": \"El Bixo\",\n" +
                    "        \"schools\": [\n" +
                    "            {\n" +
                    "                \"address\": \"Carrer Jordi Girona, 1-3\",\n" +
                    "                \"name\": \"FIB\",\n" +
                    "               \"creator\": \"Xicu Torres\"\n" +
                    "            } \n" +
                    "        ]\n" +
                    "    }\n" +
                    "}");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, ContagionLink, contagion, new Response.Listener<JSONObject>() {

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
        return success;
    }

    @Override
    public void notifyRecovery() {
        queue =  Volley.newRequestQueue(CurrentContext.getContext());
        String name = "El Bixo";
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, ContagionLink+"?nameInfected="+name,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }
}
