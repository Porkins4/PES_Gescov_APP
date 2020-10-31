package com.example.gescov.DomainLayer;

import com.example.gescov.DomainLayer.Controllers.SchoolsModelController;
import com.example.gescov.DomainLayer.Controllers.UserController;
import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolsCrontroller;

public class DomainControlFactory {
    private static SchoolsModelController schoolsModelController;
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
}
