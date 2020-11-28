package com.example.gescov.ViewLayer.Map;

import android.util.Pair;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;

import java.util.List;

public class MapController {
    private MapVIewModel mapVIewModel;

    public void getSchools(MapVIewModel mapVIewModel) {

    }

    public void getNumContagionPerSchool() {

        PresentationControlFactory.getViewLayerController().getNumContagionPerSchool();
    }

    public void sendResponseOfNumContagionPerSchool(List<Pair<School, Integer>> schools) {

        mapVIewModel.setResponse(schools);

    }

    public void setViewModelControler(MapVIewModel mapVIewModel) {
        this.mapVIewModel = mapVIewModel;
    }
}
