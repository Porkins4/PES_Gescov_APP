package com.example.gescov.viewlayer.chat.createchat;

import com.example.gescov.DomainLayer.Classmodels.School;
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
}
