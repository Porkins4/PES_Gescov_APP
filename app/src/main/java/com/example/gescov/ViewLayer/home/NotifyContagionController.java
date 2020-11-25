package com.example.gescov.ViewLayer.home;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;

public class NotifyContagionController {
    public void notifyInfected(MutableLiveData<ContagionRequestResult> result) {
        PresentationControlFactory.getViewLayerController().notifyInfected(result);
    }

    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result) {
        PresentationControlFactory.getViewLayerController().notifyRecovery(result);
    }

    public void notifyPossibleContagion(MutableLiveData<ContagionRequestResult> result) {
        PresentationControlFactory.getViewLayerController().notifyPossibleContagion(result);
    }
}
