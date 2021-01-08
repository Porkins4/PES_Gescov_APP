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
        PresentationControlFactory.getViewLayerController().createEvent(classSession);
    }

    public void getClassroomsOfSchool(String schoolID) {
        PresentationControlFactory.getViewLayerController().getClassroomsOfSchool(schoolID);
    }

    public void setClassroomsBySchoolIDResponse(boolean error, List<Classroom> classroomsFromCurrentSchool) {
        eventViewModel.setClassroomsBySchoolIDResponse(error, classroomsFromCurrentSchool);
    }

    public void getTeachersOfTheSchool(String subjectID) {
        PresentationControlFactory.getViewLayerController().getTeachersBySubjectID(subjectID);
    }

    public void notifyListOfTeachersReceivedToCreateEvent(List<User> contactsFromSelectedCenter) {
        eventViewModel.notifyListOfTeachersReceivedToCreateEvent(contactsFromSelectedCenter);
    }

    public void notifyCreateEventResponse(boolean error, int errorCode) {
        eventViewModel.notifyCreateEventResponse(error, errorCode);
    }
}
