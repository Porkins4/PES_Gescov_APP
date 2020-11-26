package com.example.gescov.DomainLayer.Classmodels;

public class ChatViewerModel {
    private String destinyID;
    private String destiny;
    private String lastMessage;
    private String lastHourMessage;

    public ChatViewerModel(String destinyID, String destiny, String lastMessage, String lastHourMessage) {
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
}
