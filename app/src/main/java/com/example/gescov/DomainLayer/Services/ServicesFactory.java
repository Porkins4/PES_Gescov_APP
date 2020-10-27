package com.example.gescov.DomainLayer.Services;

public class ServicesFactory {
    private static IContagionService iContagionservice;
    private static ISchoolService iSchoolService;


    public static IContagionService getContagionService() {
        if (iContagionservice != null) return iContagionservice;
        iContagionservice = new ContagionServiceImplementor();
        return iContagionservice;
    }

    public static ISchoolService getiSchoolService() {
        if (iSchoolService != null) return iSchoolService;
        iSchoolService = new SchoolServiceImplementor();
        return iSchoolService;
    }
}
