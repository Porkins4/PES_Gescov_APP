package com.example.gescov.PresentationLayer.ContagionList.Controllers;

import com.example.gescov.DomainLayer.Controllers.ModelController;


public class ViewLayerController {
    ModelController Mc;

    public ViewLayerController() {
        Mc = new ModelController();
    }

    public String getAllContagions() {
       return Mc.getAllContagions();

    }
}
