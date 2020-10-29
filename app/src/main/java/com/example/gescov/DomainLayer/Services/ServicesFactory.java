package com.example.gescov.DomainLayer.Services;

public class ServicesFactory {
    private static IContagionService contagionservice;
    private static ISchoolService schoolService;
    private static  INotifyService notifyService;

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

    public static INotifyService getNotifyService() {
        if (notifyService != null) return notifyService;
        notifyService = new NotifyServiceImplementor();
        return notifyService;
    }
}
