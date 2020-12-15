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

    public LiveData<Boolean> deleteChat(int position) {
        deleteChatResponse = new MutableLiveData<>();
        PresentationControlFactory.getChatListController().setChatListViewModel(this);
        PresentationControlFactory.getChatListController().deleteChat(adapter.chatID(position));
        return deleteChatResponse;
    }

    public void deleteChatFromAdapter(int position) {
        adapter.delete(position);
        adapter.notifyDataSetChanged();
    }

    public void setDeleteResult(boolean error) {
        deleteChatResponse.setValue(error);
    }

    public LiveData<Boolean> getChatPreviewModels() {
        if (updateChatPreviews == null) {
            updateChatPreviews = new MutableLiveData<>();
        }
        updateList();
        return updateChatPreviews;
    }

    public void setUpdateResult(List<ChatPreviewModel> chatPreviewModels, boolean error) {
        if (!error) {
            this.chatPreviewModels = chatPreviewModels;
            for (ChatPreviewModel x: chatPreviewModels) x.print();
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
}