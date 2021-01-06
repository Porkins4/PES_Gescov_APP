package com.example.gescov.viewlayer.event;

import com.example.gescov.domainlayer.Classmodels.ClassSessionModel;
import com.example.gescov.domainlayer.Classmodels.Classroom;
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

    public void createEvent(ClassSessionModel classSession) {

    }

    public void getClassroomsOfSchool(String schoolID) {
        PresentationControlFactory.getViewLayerController().getClassroomsOfSchool(schoolID);
    }

    public void SetClassroomsBySchoolIDResponse(boolean error, List<Classroom> classroomsFromCurrentSchool) {
        eventViewModel.SetClassroomsBySchoolIDResponse(error, classroomsFromCurrentSchool);
    }

    public void getTeachersOfTheSchool(String schoolID) {
        PresentationControlFactory.getViewLayerController().getContactsFromCenter(schoolID,3);
    }

    public void notifyListOfTeachersReceivedToCreateEvent(List<User> contactsFromSelectedCenter) {
        eventViewModel.notifyListOfTeachersReceivedToCreateEvent(contactsFromSelectedCenter);
    }
}
