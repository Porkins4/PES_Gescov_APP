package com.example.gescov.DomainLayer.Services.Volley.Interfaces;

public interface IUserService {

    void changeUserProfile(String userId, String profile);

    void getUserID(String token);

    void getUserInfo(String id);
}
