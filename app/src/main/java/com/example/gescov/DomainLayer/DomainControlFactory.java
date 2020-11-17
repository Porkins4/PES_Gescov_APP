package com.example.gescov.DomainLayer;

import com.example.gescov.DomainLayer.Controllers.ModelController;
import com.example.gescov.DomainLayer.Controllers.SchoolClassroomsModelController;
import com.example.gescov.DomainLayer.Controllers.SchoolsModelController;
import com.example.gescov.DomainLayer.Controllers.UserController;
import com.example.gescov.ViewLayer.SchoolClassroomList.SchoolClassroomsCrontroller;
import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolsCrontroller;

public class DomainControlFactory {
    private static SchoolsModelController schoolsModelController;
    private static SchoolClassroomsModelController classroomsController;
    private static UserController userController;
    private static ModelController modelController;

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

    public static SchoolClassroomsModelController getClassroomModelController() {
        if (classroomsController != null)
            return classroomsController;
        classroomsController = new SchoolClassroomsModelController();
        return classroomsController;
    }

    public static ModelController getModelController() {
        if (modelController != null)
            return modelController;
        modelController = new ModelController();
        return modelController;
    }
}