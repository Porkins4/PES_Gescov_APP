package com.example.gescov.ViewLayer;

import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolsCrontroller;

public class PresentationControlFactory {
    private static SchoolsCrontroller schoolsCrontroller;
    private static ViewLayerController viewLayerController;

    public static SchoolsCrontroller getSchoolsCrontroller() {
        if (schoolsCrontroller != null)
            return schoolsCrontroller;
        schoolsCrontroller = new SchoolsCrontroller();
        return schoolsCrontroller;
    }

    public static ViewLayerController getViewLayerController() {
        if (viewLayerController != null)
            return viewLayerController;
        viewLayerController = new ViewLayerController();
        return viewLayerController;
    }
}
