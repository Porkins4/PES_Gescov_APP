package com.example.gescov.ViewLayer.Singletons;

import android.app.Application;

import com.example.gescov.DomainLayer.Services.Volley.VolleyServices;

public class GescovApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyServices.setContext(getApplicationContext());
    }
}
