package com.example.gescov.domainlayer.Services.Volley.Interfaces;

public interface IUserService {

    void changeUserProfile(String userId, boolean isStudent);

    void getUserID(String token);

    void getUserInfo(String id);

    void setUserToken(String userID, String token);

    void deleteUserToken(String userID, String token);
}
