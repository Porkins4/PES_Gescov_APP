package com.example.gescov.viewlayer.chat.chatlist;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.ChatPreviewModel;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class ChatListViewModel extends ViewModel {
    private MutableLiveData<Boolean> updateChatPreviews;
    private List<ChatPreviewModel> chatPreviewModels;

    private MutableLiveData<Boolean> deleteChatResponse;
    private ChatListAdapter adapter;

    public ChatListAdapter getAdapter(Context c) {
        adapter = new ChatListAdapter(chatPreviewModels,c);
        return adapter;
    }

    public void setDeleteResult(boolean error) {
        deleteChatResponse.setValue(error);
    }

    public LiveData<Boolean> getChatPreviewModels() {
        updateChatPreviews = new MutableLiveData<>();
        updateList();
        return updateChatPreviews;
    }

    public void setUpdateResult(List<ChatPreviewModel> chatPreviewModels, boolean error) {
        if (!error) {
            this.chatPreviewModels = chatPreviewModels;
        }
        updateChatPreviews.setValue(error);
    }

    public void updateList() {
        PresentationControlFactory.getChatListController().setChatListViewModel(this);
        PresentationControlFactory.getChatListController().updateChatPreview();
    }

    public String getChatID(int position) {
        return chatPreviewModels.get(position).getChatID();
    }

    public String getTargetName(int position) {
        return chatPreviewModels.get(position).getTarget();
    }

    public String getTargetPic(int position) {
        return chatPreviewModels.get(position).getTargetPic();
    }

    public ChatPreviewModel getChatByPreviewByID(String chatCreatedID) {
        for (int i = 0; i < chatPreviewModels.size(); ++i) {
            if (chatPreviewModels.get(i).getChatID().equals(chatCreatedID)) {
                return chatPreviewModels.get(i);
            }
        }
        return new ChatPreviewModel();
    }
}