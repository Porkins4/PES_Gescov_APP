package com.example.gescov.ViewLayer.ClassroomDistribution;

import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.ViewLayer.ViewLayerController;

public class ClassroomDistributionController {

    private ViewLayerController viewLayerController;

    public ClassroomDistributionController() {
        viewLayerController = PresentationControlFactory.getViewLayerController();
    }

    public String getClassDimensions(String schoolId, String classroomId) {
        return viewLayerController.getClassroomDimensions(schoolId,classroomId);
    }
}
