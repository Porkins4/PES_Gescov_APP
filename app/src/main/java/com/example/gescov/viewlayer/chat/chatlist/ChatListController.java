package com.example.gescov.viewlayer.chat.chatlist;


import com.example.gescov.DomainLayer.Classmodels.ChatPreviewModel;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class ChatListController {

    private ChatListViewModel chatListViewModel;

    public ChatListController() {/*init without any colateral actions*/}

    public void deleteChat(String chatID) {
        System.out.println("stub: llamada al back-end para eliminar chat: " + chatID);
        setDeleteChatXResult(false);
    }

    public void setDeleteChatXResult(Boolean error) {
        chatListViewModel.setDeleteResult(error);
    }

    public void setChatListViewModel(ChatListViewModel chatListViewModel) {
        this.chatListViewModel = chatListViewModel;
    }

    public void updateChatPreview() {
        PresentationControlFactory.getViewLayerController().updateChatPreview();
    }

    public void chatPreviewsUpdated(List<ChatPreviewModel> chatPreviewModels, boolean error) {
        chatListViewModel.setUpdateResult(chatPreviewModels,error);
    }
}
