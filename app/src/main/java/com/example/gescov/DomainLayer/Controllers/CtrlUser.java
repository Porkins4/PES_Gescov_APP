package com.example.gescov.DomainLayer.Controllers;

import com.example.gescov.DomainLayer.Classmodels.User;

public class CtrlUser {
    private User user;

    public CtrlUser () {
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
}
