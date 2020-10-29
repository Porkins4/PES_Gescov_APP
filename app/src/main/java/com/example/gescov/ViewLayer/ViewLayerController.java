package com.example.gescov.ViewLayer;

import com.example.gescov.DomainLayer.Controllers.ModelController;


public class ViewLayerController {
    private  ModelController modelController;

    public ViewLayerController() {
        modelController = new ModelController();
    }

    public String getAllContagions() {
       return modelController.getAllContagions();
    }

    public String getAllSchools() {
       return modelController.getAllSchools();
    }

    public Boolean notifyInfected() {
        return modelController.notifyInfected();
    }
}
