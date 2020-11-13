package com.example.gescov.DomainLayer;

import com.example.gescov.DomainLayer.Controllers.SchoolsModelController;
import com.example.gescov.DomainLayer.Controllers.UserController;
import com.example.gescov.ViewLayer.SchoolClassroomList.SchoolClassroomsCrontroller;
import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolsCrontroller;

public class DomainControlFactory {
    private static SchoolsModelController schoolsModelController;
    private static SchoolClassroomsCrontroller classroomsController;
    private static UserController userController;

    public static SchoolsModelController getSchoolsModelCrontroller() {
        if (schoolsModelController != null)
            return schoolsModelController;
        schoolsModelController = new SchoolsModelController();
        return schoolsModelController;
    }

    public static UserController getUserController() {
        if (userController != null)
            return userController;
        userController = new UserController();
        return userController;
    }

    public static SchoolClassroomsCrontroller getClassroomModelController() {
        if (classroomsController != null)
            return classroomsController;
        classroomsController = new SchoolClassroomsCrontroller();
        return classroomsController;
    }
}
