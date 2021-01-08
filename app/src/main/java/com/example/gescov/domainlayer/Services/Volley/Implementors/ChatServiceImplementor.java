package com.example.gescov.domainlayer.Services.Volley.Implementors;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IChatService;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatServiceImplementor implements IChatService {
    private static final String GESCOV_CHAT_URI = "https://gescov.herokuapp.com/api/chats";
    private boolean polling;
    private boolean finish;

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
                response -> DomainControlFactory.getChatModelController().updateChatPreviewCallBack(response,false),
                error -> DomainControlFactory.getChatModelController().updateChatPreviewCallBack(null,true)
        );
        RequestQueue q = VolleyServices.getRequestQueue();
        q.add(request);
    }

    @Override
    public void getMessages(String chatID) {
        finish = false;
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
                    }, error -> { }
                    );

            VolleyServices.getRequestQueue().add(jsonObjectRequest);

        } catch (JSONException e) {
        }
    }

    @Override
    public void setPolling(boolean b) {
        this.polling = b;
    }

    @Override
    public void startPollingChat(String chatID) {
        if (polling) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET, GESCOV_CHAT_URI + "/preview?chatID=" + chatID,null,
                    response -> DomainControlFactory.getChatModelController().checkForNewMessages(response,chatID),
                    error -> { }
                    );

            VolleyServices.getRequestQueue().add(jsonObjectRequest);
        }
    }
}
