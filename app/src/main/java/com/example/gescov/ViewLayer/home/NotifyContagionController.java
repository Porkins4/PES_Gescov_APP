package com.example.gescov.ViewLayer.home;

import com.example.gescov.ViewLayer.PresentationControlFactory;

public class NotifyContagionController {
    public Boolean notifyInfected() {
       return PresentationControlFactory.getViewLayerController().notifyInfected();
    }

    public void notifyRecovery() {
        PresentationControlFactory.getViewLayerController().notifyRecovery();
    }
}
