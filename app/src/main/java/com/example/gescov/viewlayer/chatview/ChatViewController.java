package com.example.gescov.viewlayer.chatview;

import com.example.gescov.DomainLayer.Classmodels.MessageModel;
import com.example.gescov.DomainLayer.Classmodels.User;
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
}
