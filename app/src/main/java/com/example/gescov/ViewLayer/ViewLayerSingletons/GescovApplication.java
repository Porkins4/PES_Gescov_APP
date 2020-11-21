package com.example.gescov.ViewLayer.ViewLayerSingletons;

import android.app.Application;

import com.example.gescov.DomainLayer.DomainLayerSingletons.VolleyServices;

public class GescovApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyServices.setContext(getApplicationContext());
    }
}
