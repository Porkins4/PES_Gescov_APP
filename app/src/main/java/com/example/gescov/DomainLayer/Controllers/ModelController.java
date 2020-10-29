package com.example.gescov.DomainLayer.Controllers;

import com.example.gescov.DomainLayer.Conection;

public class ModelController {


    private UserController userController;
    private Conection c;

    public ModelController() {
        c = new Conection();
        userController = new UserController();
    }

    public String getAllContagions() {
       return userController.getContagionsOfMyCenter();
    }

    public void CreateUser(String nameuser) {
        userController = new UserController();
        userController.initUser();// remember to modify this when u have an user
    }

    public String getAllSchools() {
        return userController.getAllSchools();
    }

    public Boolean notifyInfected() {
        // para obtener la School
        return userController.notifyInfected();
    }
}
