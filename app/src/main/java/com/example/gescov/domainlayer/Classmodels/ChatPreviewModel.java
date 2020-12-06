package com.example.gescov.domainlayer.Classmodels;

import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ChatPreviewModel {

    private String chatID;
    private String previewID;
    private String lastMessage;
    private String lmHour;
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

    public String getLmHour() {
        return lmHour;
    }

    public String getTarget() {
        return target;
    }

    public String getTargetPic() {
        return targetPic;
    }

    public ChatPreviewModel(String chatID, String previewID, String lastMessage, String lmHour, String target, String targetPic) {
        this.chatID = chatID;
        this.previewID = previewID;
        this.lastMessage = lastMessage;
        this.lmHour = lmHour;
        this.target = target;
        this.targetPic = targetPic;
    }

    public ChatPreviewModel() {

    }

    public static ChatPreviewModel FromJSONtoChatPreview(JSONObject o) {
        ChatPreviewModel result = new ChatPreviewModel();
        try {
            String chatID = o.getString("chat");
            String previewID = o.getString("id");
            String lastMessage = o.getString("lastText");
            String lmHour = o.getString("lastTextHour");
            String target, targetPic;
            if (o.getString("userNameA").equals(DomainControlFactory.getUserModelController().getLoggedUser().getName())) {
                target = o.getString("userNameB");
                targetPic = o.getString("userPictureB");
            } else {
                target = o.getString("userNameA");
                targetPic = o.getString("userPictureA");
            }
            result = new ChatPreviewModel(chatID,previewID,lastMessage,lmHour,target,targetPic);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void print() {
        System.out.println(chatID);
        System.out.println(previewID);
        System.out.println(lastMessage);
        System.out.println(lmHour);
        System.out.println(target);
        System.out.println(targetPic);
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }

    public void addMessage(MessageModel message) {
        messages.add(message);
    }
}
