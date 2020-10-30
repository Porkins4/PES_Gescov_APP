package com.example.gescov.DomainLayer.Services;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gescov.Singletons.CurrentContext;

import org.json.JSONException;
import org.json.JSONObject;

public class NotifyServiceImplementor implements INotifyService {
    private RequestQueue Queu;
    private Boolean success;
    @Override
    public Boolean notifyContagion() {
        success = true;
        Queu =  Volley.newRequestQueue(CurrentContext.getContext());
        JSONObject contagion;
        try {
            contagion = new JSONObject("{\n" +
                    "    \"infected\":{\n" +
                    "        \"name\": \"what do u meaaaan\",\n" +
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
