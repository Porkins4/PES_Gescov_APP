package com.example.gescov.DomainLayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.IChatService;
import com.example.gescov.DomainLayer.Services.Volley.VolleyServices;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;

import org.json.JSONException;
import org.json.JSONObject;

public class ChatServiceImplementor implements IChatService {
    private static final String GESCOV_CHAT_URI = "https://gescov.herokuapp.com/api/chats";

    @Override
    public void createChat(String userid, String targetID) {
        try {
            JSONObject postData = new JSONObject();
            postData.put("partA",userid);
            postData.put("partB",targetID);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, GESCOV_CHAT_URI, postData,
                    response -> {
                        DomainControlFactory.getChatModelController().addChat(response, false);
                    }, error -> {
                if (error.networkResponse != null) {
                    DomainControlFactory.getChatModelController().addChat(null, true);
                }
            });

            VolleyServices.getRequestQueue().add(jsonObjectRequest);

        } catch (JSONException e) {
        }
    }
}
