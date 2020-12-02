package com.example.gescov.DomainLayer.Services.Volley.Interfaces;

public interface IUserService {

    void changeUserProfile(String userId, boolean isStudent);

    void getUserID(String token);

    void getUserInfo(String id);
}
