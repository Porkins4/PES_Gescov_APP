package com.example.gescov.ViewLayer.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;

public class CovidNotificationViewModel extends ViewModel {
    private MutableLiveData<ContagionRequestResult> result;

    public LiveData<ContagionRequestResult> getPostResult() {
        if (result == null) {
            result = new MutableLiveData<>();
        }
        return result;
    }

    public void getNotifyInfectedResult() {
        PresentationControlFactory.getNotifyContagionController().notifyInfected(result);
    }

    public void notifyRecoveryResult() {
        PresentationControlFactory.getNotifyContagionController().notifyRecovery(result);
    }
}
