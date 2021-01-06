package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.classroomSchedule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class ScheduleViewModel extends ViewModel {
    private MutableLiveData<Boolean> received;
    private List<Subject> l1,l2,l3,l4,l5;

    public LiveData<Boolean> getSchedule(String classID) {
        received = new MutableLiveData<>();

        PresentationControlFactory.getScheduleController().setScheduleViewModel(this);
        PresentationControlFactory.getScheduleController().getSchedule(classID);

        return received;
    }

    public void sendResponseOfSchedule(List<Subject> l1, List<Subject> l2, List<Subject> l3, List<Subject> l4, List<Subject> l5) {
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.l4 = l4;
        this.l5 = l5;
        received.setValue(true);
    }

    public List<Subject> getL1() {
        return l1;
    }

    public List<Subject> getL2() {
        return l2;
    }

    public List<Subject> getL3() {
        return l3;
    }

    public List<Subject> getL4() {
        return l4;
    }

    public List<Subject> getL5() {
        return l5;
    }
}
