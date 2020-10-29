package com.example.gescov.ViewLayer.MarkPositionInClassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gescov.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MarkPositionInClassroom extends AppCompatActivity {

    private final String POST_ASSIGNMENT_URI = "https://gescov.herokuapp.com/api/assignment";

    private Button button;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_position_in_classroom);
        context = this;
        button = (Button) findViewById(R.id.mark_position_in_classroom_button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendPostRequest();
                        finish();
                    }
                }
        );
    }

    private void sendPostRequest() {
        try {
            JSONObject postData = new JSONObject(
                    "{\n" +
                            "    \"posCol\":"+ getIntent().getExtras().getInt("col") + ",\n" +
                            "    \"posRow\":" + getIntent().getExtras().getInt("row") + ",\n" +
                            "    \"student\":{\n" +
                            "    \"name\": \"" + getIntent().getExtras().getString("studentName") + "\",\n" +
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
                            "        \"name\": \"A4S101\",\n" +
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

            RequestQueue requestQueue = Volley.newRequestQueue(this);

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
}