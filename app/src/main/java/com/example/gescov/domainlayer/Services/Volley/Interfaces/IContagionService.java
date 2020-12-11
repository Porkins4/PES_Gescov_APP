package com.example.gescov.domainlayer.Services.Volley.Interfaces;

import androidx.lifecycle.MutableLiveData;
import com.example.gescov.viewlayer.home.ContagionRequestResult;
import java.util.List;


public interface IContagionService {
    String getContagionList(String schoolId);

    void notifyContagion(MutableLiveData<ContagionRequestResult> result,String ConfirmedInfected, String id);

    void notifyRecovery(MutableLiveData<ContagionRequestResult> result , String id);

    void sendAnswers(List<Boolean> answers,String idContagion);

    void notifyPossibleContagion(String id, MutableLiveData<ContagionRequestResult> result);

    void getContagionID(String id);
}
