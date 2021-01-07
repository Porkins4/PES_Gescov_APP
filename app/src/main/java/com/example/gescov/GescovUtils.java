package com.example.gescov;

import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

public class GescovUtils {
    public static boolean isUserInSchool(User user, School school) {
        return user.getSchoolsID().contains(school.getId());
    }

    public static boolean isUserSchoolAdmin(User user, School school) {
        return school.getAdministratorsList().contains(user.getId());
    }

    public static boolean isUserAdminInAnySchool(User loggedInUser) {
        if (loggedInUser != null) {
            for (String schoolId : loggedInUser.getSchoolsID()) {
                School school = DomainControlFactory.getSchoolsModelCrontroller().getSchoolById(schoolId);
                if (school != null && school.getAdministratorsList().contains(loggedInUser.getId()))
                    return true;
            }
        }
        return false;
    }
}
