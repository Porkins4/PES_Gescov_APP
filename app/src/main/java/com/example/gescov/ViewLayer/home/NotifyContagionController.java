package com.example.gescov.ViewLayer.home;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.ViewLayer.PresentationControlFactory;

public class NotifyContagionController {
    public void notifyInfected(MutableLiveData<ContagionRequestResult> result) {
        PresentationControlFactory.getViewLayerController().notifyInfected(result);
    }

    public void notifyRecovery(MutableLiveData<ContagionRequestResult> result) {
        PresentationControlFactory.getViewLayerController().notifyRecovery(result);
    }
}
