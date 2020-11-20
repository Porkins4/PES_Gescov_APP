package com.example.gescov.ViewLayer.SchoolsAdministration.Classroom.ClassroomDistribution;

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


    public String getStudentsInClassroom(String classroom) {
        return viewLayerController.getStudentsInClassroom(classroom);
    }
}
