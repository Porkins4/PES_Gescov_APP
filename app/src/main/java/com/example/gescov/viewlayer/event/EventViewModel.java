package com.example.gescov.viewlayer.event;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class EventViewModel extends ViewModel {
    private MutableLiveData<Boolean> received;
    List<User> guests;


    public LiveData<Boolean> getGuests(String subjectID) {
        received = new MutableLiveData<>();
        PresentationControlFactory.getEventController().setViewModel(this);
        PresentationControlFactory.getEventController().getGuests(subjectID);
        return received;
    }

    public void sendResponseOfGuests(List<User> guests) {
        this.guests = guests;
        received.setValue(true);
    }

    public List<User> getListGuests() {
        return  guests;
    }
}
