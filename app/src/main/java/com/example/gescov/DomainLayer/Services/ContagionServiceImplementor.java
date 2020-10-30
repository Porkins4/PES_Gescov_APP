package com.example.gescov.DomainLayer.Services;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gescov.DomainLayer.Conection;
import com.example.gescov.Singletons.ActualContext;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ContagionServiceImplementor implements IContagionService {

    private Conection conection;
    private RequestQueue Queu;
    private Boolean success;
    public ContagionServiceImplementor() {
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
        Queu =  Volley.newRequestQueue(ActualContext.getContext());
        JSONObject contagion;
        try {
            contagion = new JSONObject("{\n" +
                    "    \"infected\":{\n" +
                    "        \"name\": \"Jhon\",\n" +
                    "        \"schools\": [\n" +
                    "            {\n" +
                    "                \"address\": \"Carrer de Pau Gargallo, 14\",\n" +
                    "                \"name\": \"FIB\",\n" +
                    "                \"state\": \"open\"\n" +
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
            Queu.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(success+" el success ");
        return success;
    }
}
