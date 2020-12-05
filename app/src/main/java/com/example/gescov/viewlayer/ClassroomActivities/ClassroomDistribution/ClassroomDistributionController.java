package com.example.gescov.viewlayer.ClassroomActivities.ClassroomDistribution;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.ViewLayerController;

public class ClassroomDistributionController {

    private ViewLayerController viewLayerController;

    public ClassroomDistributionController() {
        viewLayerController = PresentationControlFactory.getViewLayerController();
    }

    public String getClassDimensions(String schoolId, String classroomId) {
        return viewLayerController.getClassroomDimensions(schoolId,classroomId);
    }


    public String getStudentsInClassroom(String classroom) {
        return viewLayerController.getStudentsInClassroom(classroom);
    }
}
