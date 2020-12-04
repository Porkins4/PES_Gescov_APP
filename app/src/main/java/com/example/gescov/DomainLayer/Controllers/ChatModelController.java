package com.example.gescov.DomainLayer.Controllers;

import com.example.gescov.DomainLayer.Classmodels.Chat;
import com.example.gescov.DomainLayer.Classmodels.MessageModel;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;
import com.example.gescov.DomainLayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChatModelController {

    private List<Chat> chats;

    public ChatModelController() {
        chats = new ArrayList<>();
    }

    public void createChat(String targetID) {
        String userid = DomainControlFactory.getUserModelController().getLoggedUser().getId();
        ServicesFactory.getChatService().createChat(userid,targetID);
    }

    public void addChat(JSONObject response, boolean error) {
        if (!error) {
            Chat aux = Chat.FromJSONtoChat(response);
            chats.add(aux);
            DomainControlFactory.getModelController().chatCreatedInBack(aux,error);
        } else DomainControlFactory.getModelController().chatCreatedInBack(null,error);
    }

    public void getMessages(String chatID) {
        ServicesFactory.getChatService().getMessages(chatID);
    }

    public void updateChatMessages(JSONArray response, String chatID, boolean error) {
        if (!error) {
            List<MessageModel> messages = new ArrayList<>();
            for (int i = 0;  i < response.length(); ++i) {
                try {
                    messages.add(MessageModel.fromJSONtoMessage(response.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            searchAndReplaceMessages(messages,chatID);
            DomainControlFactory.getModelController().notifyChatMessagesResponse(messages, error);
        } else DomainControlFactory.getModelController().notifyChatMessagesResponse(null, error);
    }

    private void searchAndReplaceMessages(List<MessageModel> messages, String chatID) {
        boolean found = false;
        for (int i = 0; i < chats.size() && !found; ++i) {
            if (chats.get(i).getID().equals(chatID)) {
                chats.get(i).setMessages(messages);
                found = true;
            }
        }
    }
}
