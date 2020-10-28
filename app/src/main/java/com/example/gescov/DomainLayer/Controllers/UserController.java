package com.example.gescov.DomainLayer.Controllers;

import com.example.gescov.DomainLayer.Classmodels.User;

public class UserController {
    private User user;

    public UserController() {
        user = new User();
    }

    public void initUser() {
        user = new User();
    }
    public void initUser(String nameuser) {
        user = new User(nameuser);
        System.out.println(user.getName());
    }


    public String getContagionsOfMyCenter() {
        return user.getCntagionsOfCenter();
    }

    public String getAllSchools() {
        return user.getAllSchools();
    }
}
