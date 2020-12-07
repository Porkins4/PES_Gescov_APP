package com.example.gescov.viewlayer.chat.createchat;

import com.example.gescov.domainlayer.Classmodels.Chat;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class CreateChatController {

    private CreateChatViewModel createChatViewModel;

    public void setCreateChatViewModel(CreateChatViewModel createChatViewModel) {
        this.createChatViewModel = createChatViewModel;
    }

    public void updateSchools() {
        PresentationControlFactory.getViewLayerController().updateSchools();
    }

    public void notifySchoolsReceivedToCreateChatActivity() {
        createChatViewModel.notifySchoolsReceived();
    }

    public List<School> getUserSchools() {
        return PresentationControlFactory.getViewLayerController().getUserSchools();
    }

    public void getContactsFromCenter(String schoolID) {
        PresentationControlFactory.getViewLayerController().getContactsFromCenter(schoolID);
    }

    public void updateContactsFromCreateChat() {
        createChatViewModel.notifyContactsReceived();
    }

    public List<User> getContacts() {
        return PresentationControlFactory.getViewLayerController().getContacts();
    }

    public void createChat(String targetID) {
        PresentationControlFactory.getViewLayerController().createChat(targetID);
    }

    public void chatCreatedInBack(Chat chat, boolean error) {
        createChatViewModel.setCreatedChat(chat, error);
    }
}
