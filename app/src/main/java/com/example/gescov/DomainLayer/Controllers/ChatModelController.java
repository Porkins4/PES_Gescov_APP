package com.example.gescov.DomainLayer.Controllers;

import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;
import com.example.gescov.DomainLayer.Singletons.ServicesFactory;

public class ChatModelController {


    public void createChat(String targetID) {
        String userid = DomainControlFactory.getUserModelController().getLoggedUser().getId();
        ServicesFactory.getChatService().createChat(userid,targetID);
    }
}
