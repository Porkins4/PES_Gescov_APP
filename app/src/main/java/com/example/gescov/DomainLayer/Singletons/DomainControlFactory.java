package com.example.gescov.DomainLayer.Singletons;

import com.example.gescov.DomainLayer.Controllers.AssignmentsModelController;
import com.example.gescov.DomainLayer.Controllers.ChatModelController;
import com.example.gescov.DomainLayer.Controllers.ModelController;
import com.example.gescov.DomainLayer.Controllers.ClassroomsModelController;
import com.example.gescov.DomainLayer.Controllers.SchoolRequestModelController;
import com.example.gescov.DomainLayer.Controllers.SchoolsModelController;
import com.example.gescov.DomainLayer.Controllers.UserModelController;

public class DomainControlFactory {
    private static SchoolsModelController schoolsModelController;
    private static ClassroomsModelController classroomsController;
    private static UserModelController userModelController;
    private static ModelController modelController;
    private static AssignmentsModelController assignmentsModelController;
    private static SchoolRequestModelController schoolRequestModelController;
    private static ChatModelController chatModelController;

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
}