package com.example.gescov.ViewLayer.navigation.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gescov.R;

import org.json.JSONException;
import org.json.JSONObject;

public class CovidNotificationActivity extends AppCompatActivity {
    JSONObject contagion;
    RequestQueue que;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid_notification);
        que = Volley.newRequestQueue(this);
        if (getIntent().hasExtra("NameInfected")) {
            String name = getIntent().getExtras().getString("NameInfected");
            String school = getIntent().getExtras().getString("School");
            JSONObject infected = new JSONObject();
            JSONObject schools = new JSONObject();
            try {
                /*infected.put("name",name);
                schools.put("address","Carrer Jordi Girona, 3");
                schools.put("name",school);
                schools.put("state","open");
                System.out.println(schools.toString());
                contagion.put("infected",infected);
                contagion.put("schools",schools);*/
                contagion = new JSONObject("{\n" +
                        "    \"infected\":{\n" +
                        "        \"name\": \"Pablo\",\n" +
                        "        \"schools\": [\n" +
                        "            {\n" +
                        "                \"address\": \"Carrer de Pau Gargallo, 14\",\n" +
                        "                \"name\": \"FIB\",\n" +
                        "                \"state\": \"open\"\n" +
                        "            } \n" +
                        "        ]\n" +
                        "    }\n" +
                        "}");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Button notify = (Button) findViewById(R.id.notifypositive);
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                if (error.networkResponse != null  && error.networkResponse.statusCode == 400  ) {
                                   OpenError();
                                }
                            }
                        });

                // c.execute("https://gescov.herokuapp.com/api/contagion",contagion.toString()).get();
                //MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
                que.add(jsonObjectRequest);
            }
        });
    }

    private void OpenError() {
        PopErrorNotifyPositive error = new PopErrorNotifyPositive();
        error.show(getSupportFragmentManager(),"Tag");
    }

}