package com.example.gescov.DomainLayer.Services.Volley.Interfaces;

public interface IChatService {
    void createChat(String userid, String targetID);

    void updateChatPreview(String userid);
}
