package com.example.gescov.viewlayer.chat.chatview;

import com.example.gescov.domainlayer.Classmodels.MessageModel;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class ChatViewController {

    private ChatViewViewModel chatViewViewModel;

    public void setChatViewViewModel(ChatViewViewModel chatViewViewModel) {
        this.chatViewViewModel = chatViewViewModel;
    }


    public void notifyChatMessagesResponse(List<MessageModel> messages, boolean error) {
        chatViewViewModel.notifyGetChatMessagesResponse(messages, error);
    }

    public User getLoggedUser() {
        return PresentationControlFactory.getViewLayerController().getUserLoggedIn();
    }

    public void sendMessage(String chatID, String message) {
        PresentationControlFactory.getViewLayerController().sendMessage(chatID,message);
    }

    public void startGettingChat(String chatID) {
        PresentationControlFactory.getViewLayerController().startGettingChat(chatID);
    }

    public void deactivatePolling() {
        PresentationControlFactory.getViewLayerController().deactivatePolling();
    }

    public void startPolling(String chatID) {
        PresentationControlFactory.getViewLayerController().startPollingChat(chatID);
    }
}
