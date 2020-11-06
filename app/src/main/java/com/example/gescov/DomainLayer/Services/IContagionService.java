package com.example.gescov.DomainLayer.Services;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.ViewLayer.home.ContagionRequestResult;

public interface IContagionService {
    String getContagionList(String userId, String schoolId);
    void notifyContagion(MutableLiveData<ContagionRequestResult> result);
    void notifyRecovery();
}
