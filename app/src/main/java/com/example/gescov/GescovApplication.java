package com.example.gescov;

import android.app.Application;
import android.content.Context;

import com.example.gescov.Singletons.VolleyServices;

public class GescovApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyServices.setContext(getApplicationContext());
    }
}
