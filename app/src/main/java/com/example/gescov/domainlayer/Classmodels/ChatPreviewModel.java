package com.example.gescov.domainlayer.Classmodels;

import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChatPreviewModel {

    private String chatID;
    private String previewID;
    private String lastMessage;
    private String date;
    private String hour;
    private String target;
    private String targetPic;
    private List<MessageModel> messages;

    public String getChatID() {
        return chatID;
    }

    public String getPreviewID() {
        return previewID;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getHour() {
        return hour;
    }

    public String getTarget() {
        return target;
    }

    public String getTargetPic() {
        return targetPic;
    }

    public String getDate() { return date;}

    public ChatPreviewModel(String chatID, String previewID, String lastMessage, String date, String hour, String target, String targetPic) {
        this.chatID = chatID;
        this.previewID = previewID;
        this.lastMessage = lastMessage;
        this.date = date;
        this.hour = hour;
        this.target = target;
        this.targetPic = targetPic;
        messages = new ArrayList<>();
    }

    public ChatPreviewModel() {

    }

    public static ChatPreviewModel FromJSONtoChatPreview(JSONObject o) {
        ChatPreviewModel result = new ChatPreviewModel();
        try {
            String chatID = o.getString("chat");
            String previewID = o.getString("id");
            String message = null;
            String date = null;
            String hour = null;
            if (!o.getString("lastMessage").equals("null")) {
                JSONObject lastMessage = new JSONObject(o.getString("lastMessage"));
                message = lastMessage.getString("text");
                date = lastMessage.getString("date");
                hour = lastMessage.getString("hour");
            }

            String target, targetPic;
            if (o.getString("userNameA").equals(DomainControlFactory.getUserModelController().getLoggedUser().getName())) {//aquí es el id
                target = o.getString("userNameB");
                targetPic = o.getString("userPictureB");
            } else {
                target = o.getString("userNameA");
                targetPic = o.getString("userPictureA");
            }
            result = new ChatPreviewModel(chatID,previewID,message,date,hour,target,targetPic);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void print() {
        System.out.println(chatID);
        System.out.println(previewID);
        System.out.println(lastMessage);
        System.out.println(hour);
        System.out.println(target);
        System.out.println(targetPic);
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }

    public void addMessage(MessageModel message) {
        messages.add(message);
    }


    public List<MessageModel> getMessages() {
        return messages;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
