package com.example.gescov.DomainLayer.Services;

public class ServicesFactory {
    private static IContagionService icontagionservice  = new ContagionServiceImplementor();
    public static IContagionService getContagionService() {
        return icontagionservice;
    }
}
