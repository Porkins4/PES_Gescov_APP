package com.example.gescov.DomainLayer.Services;

import java.util.List;

public interface IContagionService {
     void sendAnswers(List<Boolean> answers);


    String getContagionList(String userId, String schoolId);
    Boolean notifyContagion();
}
