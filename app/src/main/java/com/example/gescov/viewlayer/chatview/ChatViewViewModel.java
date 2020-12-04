package com.example.gescov.viewlayer.chatview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.DomainLayer.Classmodels.MessageModel;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class ChatViewViewModel extends ViewModel {

    private String userName;
    private String targetID;
    private MutableLiveData<Boolean> updated;
    private List<MessageModel> messages;

    public void setUserInfo(String targetName, String targetID) {
        this.userName = targetName;
        this.targetID = targetID;
    }

    public String getUserName() {
        return userName;
    }

    public LiveData<Boolean> updateChat(String chatID) {
        if (updated == null) {
            updated = new MutableLiveData<>();
            PresentationControlFactory.getChatViewController().setChatViewViewModel(this);
            PresentationControlFactory.getChatViewController().getChatMessages(chatID);
        }
        return updated;
    }

    public void notifyChatMessagesResponse(List<MessageModel> messages, boolean error) {
        if (!error) {
            this.messages = messages;
        }
        updated.setValue(error);
    }

    public MessageAdapter getAdapter(ChatViewActivity instance) {
        String userID = PresentationControlFactory.getChatViewController().getLoggedUser().getId();
        return new MessageAdapter(instance,messages,userID);
    }
}
