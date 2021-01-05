package com.example.gescov.viewlayer.event;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.ClassSessionModel;
import com.example.gescov.domainlayer.Classmodels.Classroom;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.ArrayList;
import java.util.List;

public class EventViewModel extends ViewModel {
    private MutableLiveData<Boolean> received;
    List<User> guests;
    List<Classroom> classrooms;
    private List<User> teachers;
    private String subjectID;
    private String schoolID;

    public EventViewModel() {
        PresentationControlFactory.getEventController().setViewModel(this);
        guests = new ArrayList<>();
        classrooms = new ArrayList<>();
        teachers = new ArrayList<>();
    }

    public LiveData<Boolean> getGuests() {
        received = new MutableLiveData<>();
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

    public LiveData<Boolean> createEvent(String concept, String hour, String finishHour, String date, String teacherID, String classroomID, String subjectID) {
        received = new MutableLiveData<>();
        ClassSessionModel classSession= new ClassSessionModel("",hour,finishHour,date,classroomID,subjectID,teacherID,concept);
        PresentationControlFactory.getEventController().createEvent(classSession);
        return received;
    }

    public void init(String schoolID, String subjectID) {
        this.schoolID = schoolID;
        this.subjectID = subjectID;
    }

    public LiveData<Boolean> getClassrooms() {
        received = new MutableLiveData<>();
        PresentationControlFactory.getEventController().getClassroomsOfSchool(schoolID);
        return received;
    }

    public void SetClassroomsBySchoolIDResponse(boolean error, List<Classroom> classroomsFromCurrentSchool) {
        if (!error) {
            this.classrooms = classroomsFromCurrentSchool;
        }
        received.setValue(error);
    }

    public LiveData<Boolean> getTeachersOfTheSchool() {
        received = new MutableLiveData<>();
        PresentationControlFactory.getEventController().getTeachersOfTheSchool(schoolID);
        return received;
    }

    public void notifyListOfTeachersReceivedToCreateEvent(List<User> contactsFromSelectedCenter) {
        this.teachers = contactsFromSelectedCenter;
        received.setValue(true);
    }

    public String[] getTeacherNames() {
        String[] result = new String[teachers.size()];
        for (int i = 0; i < teachers.size(); ++i) {
            System.out.println(teachers.get(i).getName());
            result[i] = teachers.get(i).getName();
        }
        return result;
    }

    public String[] getClassroomNames() {
        String[] result = new String[classrooms.size()];
        System.out.println("--------------");
        for (int i = 0; i < classrooms.size(); ++i) {
            System.out.println(classrooms.get(i).getName());
            result[i] = classrooms.get(i).getName();
        }
        return result;
    }

    public boolean emptyClassrooms() {
        return classrooms.isEmpty();
    }
}
