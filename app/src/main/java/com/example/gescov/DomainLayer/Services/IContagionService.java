package com.example.gescov.DomainLayer.Services;

public interface IContagionService {
    String getContagionList(String userId, String schoolId);
    Boolean notifyContagion();
}
