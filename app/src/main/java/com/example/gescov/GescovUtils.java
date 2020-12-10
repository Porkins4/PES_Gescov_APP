package com.example.gescov;

import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.User;

public class GescovUtils {
    public static boolean isUserInSchool(User user, School school) {
        return user.getSchoolsID().contains(school.getId());
    }

    public static boolean isUserSchoolAdmin(User user, School school) {
        return school.getAdministratorsList().contains(user.getId());
    }
}
