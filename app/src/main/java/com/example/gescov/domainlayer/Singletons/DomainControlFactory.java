package com.example.gescov.domainlayer.Singletons;

import com.example.gescov.domainlayer.Controllers.AssignmentsModelController;
import com.example.gescov.domainlayer.Controllers.ChatModelController;
import com.example.gescov.domainlayer.Controllers.ClassSessionController;
import com.example.gescov.domainlayer.Controllers.ClassroomsModelController;
import com.example.gescov.domainlayer.Controllers.EventModelController;
import com.example.gescov.domainlayer.Controllers.ForumModelController;
import com.example.gescov.domainlayer.Controllers.ModelController;
import com.example.gescov.domainlayer.Controllers.ScheduleModelController;
import com.example.gescov.domainlayer.Controllers.SchoolRequestModelController;
import com.example.gescov.domainlayer.Controllers.SchoolsModelController;
import com.example.gescov.domainlayer.Controllers.SubjectModelController;
import com.example.gescov.domainlayer.Controllers.TracingTestResultController;
import com.example.gescov.domainlayer.Controllers.UserModelController;

public class DomainControlFactory {
    private static SchoolsModelController schoolsModelController;
    private static ClassroomsModelController classroomsController;
    private static UserModelController userModelController;
    private static ModelController modelController;
    private static AssignmentsModelController assignmentsModelController;
    private static SchoolRequestModelController schoolRequestModelController;
    private static ChatModelController chatModelController;
    private static SubjectModelController subjectModelController;

    private static EventModelController eventModelController;

    private static ForumModelController forumModelController;
    private static TracingTestResultController tracingTestResultController;
    private static ClassSessionController classSessionController;
    private static ScheduleModelController scheduleModelController;


    public static SchoolsModelController getSchoolsModelCrontroller() {
        if (schoolsModelController != null)
            return schoolsModelController;
        schoolsModelController = new SchoolsModelController();
        return schoolsModelController;
    }

    public static UserModelController getUserModelController() {
        if (userModelController != null)
            return userModelController;
        userModelController = new UserModelController();
        return userModelController;
    }

    public static ClassroomsModelController getClassroomModelController() {
        if (classroomsController != null)
            return classroomsController;
        classroomsController = new ClassroomsModelController();
        return classroomsController;
    }

    public static ModelController getModelController() {
        if (modelController != null)
            return modelController;
        modelController = new ModelController();
        return modelController;
    }

    public static AssignmentsModelController getAssignmentModelController() {
        if (assignmentsModelController != null)
            return assignmentsModelController;
        assignmentsModelController = new AssignmentsModelController();
        return assignmentsModelController;
    }

    public static SchoolRequestModelController getSchoolRequestModelController() {
        if (schoolRequestModelController != null)
            return schoolRequestModelController;
        schoolRequestModelController = new SchoolRequestModelController();
        return schoolRequestModelController;
    }

    public static ChatModelController getChatModelController() {
        if (chatModelController != null)
            return chatModelController;
        chatModelController = new ChatModelController();
        return chatModelController;
    }

    public static SubjectModelController getSubjectModelController() {
        if (subjectModelController != null)
            return subjectModelController;
        subjectModelController = new SubjectModelController();
        return subjectModelController;
    }


    public static EventModelController getEventModelController() {
        if (eventModelController != null)
            return eventModelController;
        eventModelController = new EventModelController();
        return eventModelController;
    }

    public static ForumModelController getForumModelController() {
        if (forumModelController != null)
            return forumModelController;
        forumModelController = new ForumModelController();
        return forumModelController;
    }


    public static TracingTestResultController getTracingTestResultModel() {
        if (tracingTestResultController != null)
            return tracingTestResultController;
        tracingTestResultController = new TracingTestResultController();
        return tracingTestResultController;
    }
    public static ClassSessionController getClassSessionsModelController() {
        if (classroomsController != null)
            return classSessionController;
            classSessionController = new ClassSessionController();
        return classSessionController;

    }

    public static ScheduleModelController getScheduleModelController() {
        if (scheduleModelController != null)
            return scheduleModelController;
        scheduleModelController = new ScheduleModelController();
        return scheduleModelController;
    }


}