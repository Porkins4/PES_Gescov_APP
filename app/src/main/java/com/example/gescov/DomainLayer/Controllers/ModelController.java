package com.example.gescov.DomainLayer.Controllers;

import com.example.gescov.DomainLayer.Conection;

import java.util.concurrent.ExecutionException;

public class ModelController {


    CtrlUser userController;
    Conection c;

    public ModelController() {
        c = new Conection();
        userController = new CtrlUser ();
    }

    public String getAllContagions() {
       return userController.getContagionsOfMyCenter();
    }
    public void CreateUser(String nameuser) {
        userController = new CtrlUser();
        userController.initUser();// remember to modify this when u have an user
    }
}
