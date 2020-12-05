package com.example.gescov.viewlayer.home;

import android.location.Location;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

public class HomeController {
    public void setLocation(Location location) {
        PresentationControlFactory.getViewLayerController().setLocation(location);
    }
}
