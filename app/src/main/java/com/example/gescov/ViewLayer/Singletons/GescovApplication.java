package com.example.gescov.ViewLayer.Singletons;

import android.app.Application;
import android.content.Context;

import com.example.gescov.DomainLayer.Services.Volley.VolleyServices;

public class GescovApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyServices.setContext(getApplicationContext());
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
