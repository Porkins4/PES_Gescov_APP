package com.example.gescov.DomainLayer.Services;

public class ServicesFactory {
    private static IContagionService contagionservice;
    private static ISchoolService schoolService;


    public static IContagionService getContagionService() {
        if (contagionservice != null) return contagionservice;
        contagionservice = new ContagionServiceImplementor();
        return contagionservice;
    }

    public static ISchoolService getSchoolService() {
        if (schoolService != null) return schoolService;
        schoolService = new SchoolServiceImplementor();
        return schoolService;
    }
}
