package com.example.gescov.viewlayer.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

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

    public void notifyPossibleContagion() {
        PresentationControlFactory.getNotifyContagionController().notifyPossibleContagion(result);

    }
}
