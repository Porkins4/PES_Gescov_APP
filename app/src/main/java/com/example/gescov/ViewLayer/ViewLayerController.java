package com.example.gescov.ViewLayer;

import com.example.gescov.DomainLayer.Controllers.ModelController;


public class ViewLayerController {
    ModelController Mc;

    public ViewLayerController() {
        Mc = new ModelController();
    }

    public String getStudentsInClassroom(String classroom) {
        return Mc.getStudentsInClassroom(classroom);
    }

    public String getAllContagions() {
       return Mc.getAllContagions();

    }

    public String getClassroomDimensions(String schoolId, String classroomId) {
        return Mc.getClassroomDimensions(schoolId, classroomId);
    }
}
