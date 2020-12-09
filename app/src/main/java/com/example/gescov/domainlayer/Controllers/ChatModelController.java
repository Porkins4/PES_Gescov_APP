package com.example.gescov.domainlayer.Controllers;

import com.example.gescov.domainlayer.Classmodels.Chat;
import com.example.gescov.domainlayer.Classmodels.ChatPreviewModel;
import com.example.gescov.domainlayer.Classmodels.MessageModel;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.domainlayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChatModelController {

    private List<Chat> chats;
    private List<ChatPreviewModel> chatPreviewModels;

    public ChatModelController() {
        chats = new ArrayList<>();
        chatPreviewModels = new ArrayList<>();
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

    public void updateChatPreview() {
        chatPreviewModels = new ArrayList<>();
        String userid = DomainControlFactory.getUserModelController().getLoggedUser().getId();
        ServicesFactory.getChatService().updateChatPreview(userid);
    }

    public void updateChatPreviewCallBack(JSONArray response, boolean error) {
        if (!error) {
            for (int i = 0; i < response.length(); ++i) {
                try {
                    chatPreviewModels.add(ChatPreviewModel.FromJSONtoChatPreview(response.getJSONObject(i)));
                    //chatPreviewModels.get(chatPreviewModels.size()-1).print();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            DomainControlFactory.getModelController().chatPreviewsUpdated(chatPreviewModels,error);
        } else DomainControlFactory.getModelController().chatPreviewsUpdated(chatPreviewModels,error);
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
            //messages.get(messages.size()-1).print();
            searchAndReplaceMessages(messages,chatID);
            DomainControlFactory.getModelController().notifyChatMessagesResponse(messages, error);
        } else DomainControlFactory.getModelController().notifyChatMessagesResponse(null, error);
    }

    private void searchAndReplaceMessages(List<MessageModel> messages, String chatID) {
        boolean found = false;
        for (int i = 0; i < chatPreviewModels.size() && !found; ++i) {
            if (chatPreviewModels.get(i).getChatID().equals(chatID)) {
                chatPreviewModels.get(i).setMessages(messages);
                found = true;
            }
        }
    }

    public void sendMessage(String chatID, String message) {
        ServicesFactory.getChatService().sendMessage(chatID,message,DomainControlFactory.getUserModelController().getLoggedUser().getId());
    }

    public void addMessageToChat(JSONObject response) {
        MessageModel message = MessageModel.fromJSONtoMessage(response);
        for (int i = 0; i < chatPreviewModels.size(); ++i) {
            if (chatPreviewModels.get(i).getChatID().equals(message.getChatID())) {
                chatPreviewModels.get(i).addMessage(message);
            }
        }
        DomainControlFactory.getModelController().notifyChatUpdated();
    }

    public void startGettingChat(String chatID) {
        ServicesFactory.getChatService().setPolling(true);
        ServicesFactory.getChatService().startPollingChat(chatID);
        getMessages(chatID);
        DomainControlFactory.getModelController().notifyChatMessagesResponse(getChatPreviewBychatID(chatID).getMessages(),false);
    }

    public void checkForNewMessages(JSONObject response, String chatID) {
        ChatPreviewModel temp = ChatPreviewModel.FromJSONtoChatPreview(response);
        ChatPreviewModel current = getChatPreviewBychatID(chatID);
        if (!current.getHour().equals(temp.getHour()) ||  !current.getDate().equals(temp.getDate())) {
            current.setHour(temp.getHour());
            current.setDate(temp.getDate());
            getMessages(chatID);
            System.out.println("new message bro :)");
        }
        ServicesFactory.getChatService().startPollingChat(chatID);
    }

    private ChatPreviewModel getChatPreviewBychatID(String chatID) {
        for (int i = 0; i < chatPreviewModels.size(); ++i) {
            ChatPreviewModel aux = chatPreviewModels.get(i);
            if (aux.getChatID().equals(chatID)) return aux;
        }
        return null;
    }

    public void deactivatePolling() {
        ServicesFactory.getChatService().setPolling(false);
    }
}
