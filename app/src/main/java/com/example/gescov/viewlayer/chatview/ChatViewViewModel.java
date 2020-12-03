package com.example.gescov.viewlayer.chatview;

import androidx.lifecycle.ViewModel;

public class ChatViewViewModel extends ViewModel {

    private String userName;
    private String targetID;

    public void setUserInfo(String targetName, String targetID) {
        this.userName = targetName;
        this.targetID = targetID;
    }

    public String getUserName() {
        return userName;
    }
}
