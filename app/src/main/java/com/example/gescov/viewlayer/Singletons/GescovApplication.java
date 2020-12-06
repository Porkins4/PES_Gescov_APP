package com.example.gescov.viewlayer.Singletons;

import android.app.Application;
import android.content.Context;

import com.example.gescov.domainlayer.Services.Volley.VolleyServices;

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
