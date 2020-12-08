package com.example.gescov.domainlayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IChatService;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class ChatServiceImplementor implements IChatService {
    private static final String GESCOV_CHAT_URI = "https://gescov.herokuapp.com/api/chats";

    private static final String DATE_FORMAT = "dd-MM-yyyy";


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

    @Override
    public void updateChatPreview(String userid) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, GESCOV_CHAT_URI + "/previews?userID=" + userid,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        DomainControlFactory.getChatModelController().updateChatPreviewCallBack(response,false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DomainControlFactory.getChatModelController().updateChatPreviewCallBack(null,true);
                    }
                }
        );
        RequestQueue q = VolleyServices.getRequestQueue();
        q.add(request);
    }

    @Override
    public void getMessages(String chatID) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, GESCOV_CHAT_URI + "/" + chatID + "/messages/",null,
                response -> DomainControlFactory.getChatModelController().updateChatMessages(response, chatID, false),
                error -> DomainControlFactory.getChatModelController().updateChatMessages(null,chatID,true)
        );

        VolleyServices.getRequestQueue().add(request);
    }

    @Override
    public void sendMessage(String chatID, String message, String id) {
        try {
            JSONObject postData = new JSONObject();
            postData.put("creator",id);
            postData.put("chat",chatID);
            postData.put("text",message);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            postData.put("date",dtf.format(LocalDate.now()));

            dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            postData.put("hour",dtf.format(LocalDateTime.now()));


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, GESCOV_CHAT_URI+"/new", postData,
                    response -> {
                        DomainControlFactory.getChatModelController().addMessageToChat(response);
                    }, error -> {
                if (error.networkResponse != null) {

                }
            });

            VolleyServices.getRequestQueue().add(jsonObjectRequest);

        } catch (JSONException e) {
        }
    }


}
