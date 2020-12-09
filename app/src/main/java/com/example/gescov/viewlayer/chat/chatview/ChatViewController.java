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

    public void getChatMessages(String chatID) {
        PresentationControlFactory.getViewLayerController().getChatMessages(chatID);
    }

    public void notifyChatMessagesResponse(List<MessageModel> messages, boolean error) {
        chatViewViewModel.notifyChatMessagesResponse(messages, error);
    }

    public User getLoggedUser() {
        return PresentationControlFactory.getViewLayerController().getUserLoggedIn();
    }

    public void sendMessage(String chatID, String message) {
        PresentationControlFactory.getViewLayerController().sendMessage(chatID,message);
    }

    public void notifyChatUpdated() {
        chatViewViewModel.notifyChatUpdated();
    }

    public void startGettingChat(String chatID) {
        PresentationControlFactory.getViewLayerController().startGettingChat(chatID);
    }

    public void setChatObserver() {
    }

    public void deactivatePolling() {
        PresentationControlFactory.getViewLayerController().deactivatePolling();
    }
}
