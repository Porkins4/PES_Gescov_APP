package com.example.gescov.domainlayer.Services.Volley.Interfaces;

public interface IChatService {
    void createChat(String userid, String targetID);

    void updateChatPreview(String userid);

    void getMessages(String chatID);

    void sendMessage(String chatID, String message, String id);
}
