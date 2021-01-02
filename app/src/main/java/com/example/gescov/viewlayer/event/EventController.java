package com.example.gescov.viewlayer.event;

import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class EventController {
    EventViewModel eventViewModel;
    public void setViewModel(EventViewModel eventViewModel) {
        this.eventViewModel = eventViewModel;
    }

    public void getGuests(String subjectID) {
        PresentationControlFactory.getViewLayerController().getGuests(subjectID);
    }

    public void sendResponseOfGuests(List<User> guests) {
        eventViewModel.sendResponseOfGuests(guests);
    }
}
