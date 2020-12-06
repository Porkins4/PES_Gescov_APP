package com.example.gescov.viewlayer.Map;

import android.location.Location;
import android.util.Pair;

import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class MapController {
    private MapVIewModel mapVIewModel;

    public void getNumContagionPerSchool() {

        PresentationControlFactory.getViewLayerController().getNumContagionPerSchool(1);
    }

    public void sendResponseOfNumContagionPerSchool(List<Pair<School, Integer>> schools) {

        mapVIewModel.setResponse(schools);

    }

    public void setViewModelControler(MapVIewModel mapVIewModel) {
        this.mapVIewModel = mapVIewModel;
    }

    public Location getLocation() {
        return PresentationControlFactory.getViewLayerController().getLocation();
    }
}
