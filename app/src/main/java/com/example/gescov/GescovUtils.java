package com.example.gescov;

import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.Subject;
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

    public static String getNormalizedTime(String time) {
        String year = time.substring(0, 4);
        String month = time.substring(5,7);
        String day = time.substring(8, 10);
        String hour = time.substring(11, 16);
        return day + "/" + month + "/" + year + " " + hour;
    }

    public static boolean isEnroled(String subjectID) {
        Subject s = DomainControlFactory.getSubjectModelController().getSubjectsFromUserBySubjectID(subjectID);
        return s.getId().equals(subjectID);
    }
}
