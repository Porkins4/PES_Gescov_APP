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

    //stub at the moment
    public boolean containsSchool(String schoolId) {
        return true;
    }

    //el colegio es contenido por el usuario
    public String getClassroomDimensions(String schoolId, String classroomId) {
        return user.getClassroomDimensions(schoolId,classroomId);
    }
}
