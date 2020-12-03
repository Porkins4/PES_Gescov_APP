package com.example.gescov.viewlayer.chat.chatlist;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.DomainLayer.Classmodels.ChatViewerModel;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.ArrayList;
import java.util.List;

public class ChatListViewModel extends ViewModel {
    private MutableLiveData<Boolean> updateChatPreviews;

    private MutableLiveData<Boolean> deleteChatResponse;
    private ChatListAdapter adapter;

    public ChatListAdapter getAdapter(Context c) {
        List<ChatViewerModel> l = new ArrayList<>();
        l.add(new ChatViewerModel("ajhfaklsjfhjks0","algunID","Anas Infad","parece que sigue petando la app...","01:27"));
        l.add(new ChatViewerModel("ddhaslkjdhklja1", "algunID","Victor Murillo","Venga Isaac, que nos sacamos el chat!","04:00"));
        l.add(new ChatViewerModel("ddhaslkjdhklja2", "algunID","Pablo Cebollada","Me gustan las pipas :)","13:00"));
        l.add(new ChatViewerModel("ddhaslkjdhklja3", "algunID","Ponç Parramon","Como Scrum Master voy a daros latigazos...","20:15"));
        l.add(new ChatViewerModel("ddhaslkjdhklja4", "algunID","Albert Maldonado","Arregla ya la G de google >:(","01.30"));
        adapter = new ChatListAdapter(l,c);
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

    public LiveData<Boolean> getChatPreviews() {
        if (updateChatPreviews == null) {
            updateChatPreviews = new MutableLiveData<>();
            PresentationControlFactory.getChatListController().setChatListViewModel(this);
            PresentationControlFactory.getChatListController().updateChatPreview();
        }
        return updateChatPreviews;
    }
}