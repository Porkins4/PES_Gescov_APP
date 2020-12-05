package com.example.gescov.viewlayer.chatview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.DomainLayer.Classmodels.MessageModel;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class ChatViewViewModel extends ViewModel {

    private String userName;
    private String targetPic;
    private MutableLiveData<Boolean> updated;
    private List<MessageModel> messages;
    private String chatID;
    private MessageAdapter adapter;

    public void setUserInfo(String targetName, String targetPic) {
        this.userName = targetName;
        this.targetPic = targetPic;
    }

    public String getUserName() {
        return userName;
    }

    public LiveData<Boolean> updateChat() {
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
        adapter = new MessageAdapter(instance,messages,userID,targetPic);
        return adapter;
    }

    public void setChatID(String chatID) {
        this.chatID =chatID;
    }

    public void sendMessage(String message) {
        PresentationControlFactory.getChatViewController().sendMessage(chatID, message);
    }

    public int getLastElemPos() {
        return messages.size()-1;
    }

    public void notifyChatUpdated() {
        adapter.notifyDataSetChanged();
        adapter.getIth(adapter.size()-1).print();
        System.out.println("sdaslkdhñalskhdalshd");
    }
}
