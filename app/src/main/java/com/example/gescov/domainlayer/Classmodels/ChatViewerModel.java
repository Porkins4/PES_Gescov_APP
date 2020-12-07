package com.example.gescov.domainlayer.Classmodels;

public class ChatViewerModel {
    private String chatID;
    private String destinyID;
    private String destiny;
    private String lastMessage;
    private String lastHourMessage;

    public ChatViewerModel(String chatID, String destinyID, String destiny, String lastMessage, String lastHourMessage) {
        this.chatID = chatID;
        this.destinyID = destinyID;
        this.destiny = destiny;
        this.lastMessage = lastMessage;
        this.lastHourMessage = lastHourMessage;
    }

    public String getDestinyID() {
        return destinyID;
    }

    public String getDestiny() {
        return destiny;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getLastHourMessage() {
        return lastHourMessage;
    }

    public String getChatID() {
        return chatID;
    }

    public void setDeleteResult(boolean b) {
    }
}
