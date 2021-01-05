package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.classroomSchedule;

import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class ScheduleController {
    ScheduleViewModel scheduleViewModel;
    public void setSchedule(String classID, List<Subject> l1, List<Subject> l2, List<Subject> l3, List<Subject> l4, List<Subject> l5) {
        PresentationControlFactory.getViewLayerController().setSchedule(classID,l1,l2,l3,l4,l5);
    }

    public void sendResponseOfSchedule(List<Subject> l1, List<Subject> l2, List<Subject> l3, List<Subject> l4, List<Subject> l5) {
        scheduleViewModel.sendResponseOfSchedule(l1,l2,l3,l4,l5);
    }

    public void setScheduleViewModel(ScheduleViewModel scheduleViewModel) {
        this.scheduleViewModel = scheduleViewModel;
    }

    public void getSchedule(String classID) {

        PresentationControlFactory.getViewLayerController().getSchedule(classID);
    }
}
