package com.example.gescov.ViewLayer.chatlist;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.gescov.DomainLayer.Classmodels.ChatViewerModel;

import java.util.ArrayList;
import java.util.List;

public class ChatListViewModel extends ViewModel {

    public ChatListAdapter getAdapter(Context c) {
        List<ChatViewerModel> l = new ArrayList<>();
        l.add(new ChatViewerModel("algunID","Anas Infad","parece que sigue petando la app...","01:27"));
        l.add(new ChatViewerModel("algunID","Victor Murillo","Venga Isaac, que nos sacamos el chat!","04:00"));
        l.add(new ChatViewerModel("algunID","Pablo Cebollada","Me gustan las pipas :)","13:00"));
        l.add(new ChatViewerModel("algunID","Ponç Parramon","Como Scrum Master voy a daros latigazos...","20:15"));
        l.add(new ChatViewerModel("algunID","Albert Maldonado","Arregla ya la G de google >:(","01.30"));
        ChatListAdapter aux = new ChatListAdapter(l,c);
        return aux;
    }
    // TODO: Implement the ViewModel
}