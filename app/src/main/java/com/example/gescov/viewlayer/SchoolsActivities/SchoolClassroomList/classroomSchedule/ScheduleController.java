package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.classroomSchedule;

import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class ScheduleController {
    public void setSchedule(String classID, List<Subject> l1, List<Subject> l2, List<Subject> l3, List<Subject> l4, List<Subject> l5) {
        PresentationControlFactory.getViewLayerController().setSchedule(classID,l1,l2,l3,l4,l5);
    }
}
