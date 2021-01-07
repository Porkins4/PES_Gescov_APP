package com.example.gescov.viewlayer.chat.chatview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.MessageModel;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class ChatViewViewModel extends ViewModel {

    private String userName;
    private String targetPic;
    private List<MessageModel> messages;
    private String chatID;

    //polling method
    private MutableLiveData<Boolean> messageReceived;

    public ChatViewViewModel() {
        PresentationControlFactory.getChatViewController().setChatViewViewModel(this);
    }

    public void setUserInfo(String targetName, String targetPic, String chatID) {
        this.userName = targetName;
        this.targetPic = targetPic;
        this.chatID = chatID;
    }


    public String getUserName() {
        return userName;
    }


    public void sendMessage(String message) {
        PresentationControlFactory.getChatViewController().sendMessage(chatID, message);
    }

    public LiveData<Boolean> getMessages() {
        if (messageReceived == null) {
            messageReceived = new MutableLiveData<>();
            PresentationControlFactory.getChatViewController().startGettingChat(chatID);
        }
        return messageReceived;
    }


    public void notifyGetChatMessagesResponse(List<MessageModel> messages, boolean error) {
        this.messages = messages;
        messageReceived.setValue(error);
    }

    public MessageAdapter getAdapter(ChatViewActivity instance) {
        String userID = PresentationControlFactory.getChatViewController().getLoggedUser().getId();
        return new MessageAdapter(instance,messages,userID,targetPic);
    }


    public void deactivatePolling() {
        PresentationControlFactory.getChatViewController().deactivatePolling();
    }

    public LiveData<Boolean> startPolling() {
        messageReceived = new MutableLiveData<>();
        PresentationControlFactory.getChatViewController().startPolling(chatID);
        return messageReceived;
    }
}
