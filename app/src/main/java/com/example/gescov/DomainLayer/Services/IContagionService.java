package com.example.gescov.DomainLayer.Services;

import androidx.lifecycle.MutableLiveData;
import com.example.gescov.ViewLayer.home.ContagionRequestResult;
import java.util.List;


public interface IContagionService {
    String getContagionList(String userId, String schoolId);
    void notifyContagion(MutableLiveData<ContagionRequestResult> result,String ConfirmedInfected, String id);
    void notifyRecovery(MutableLiveData<ContagionRequestResult> result , String id);
    void sendAnswers(List<Boolean> answers,String idContagion);
}
