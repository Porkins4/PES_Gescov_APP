package com.example.gescov.ViewLayer;

import com.example.gescov.ViewLayer.ContagionList.ContagionController;
import com.example.gescov.ViewLayer.MarkPositionInClassroom.MarkPositionInClassroom;
import com.example.gescov.ViewLayer.MarkPositionInClassroom.MarkPositionInClassroomController;
import com.example.gescov.ViewLayer.SchoolClassroomList.SchoolClassroomsCrontroller;
import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolsCrontroller;
import com.example.gescov.ViewLayer.home.NotifyContagionController;

public class PresentationControlFactory {
    private static ViewLayerController viewLayerController;
    private static SchoolsCrontroller schoolsCrontroller;
    private static SchoolClassroomsCrontroller classroomsCrontroller;
    private static ContagionController contagionCrontroller;
    private static NotifyContagionController notifyContagionController;
    private static MarkPositionInClassroomController markPositionInClassroomController;

    public static ViewLayerController getViewLayerController() {
        if (viewLayerController != null)
            return viewLayerController;
        viewLayerController = new ViewLayerController();
        return viewLayerController;
    }
    public static SchoolsCrontroller getSchoolsCrontroller() {
        if (schoolsCrontroller != null)
            return schoolsCrontroller;
        schoolsCrontroller = new SchoolsCrontroller();
        return schoolsCrontroller;
    }

    public static SchoolClassroomsCrontroller getClassroomsCrontroller() {
        if (classroomsCrontroller != null)
            return classroomsCrontroller;
        classroomsCrontroller = new SchoolClassroomsCrontroller();
        return classroomsCrontroller;
    }

    public static ContagionController getContagionController() {
        if (contagionCrontroller != null)
            return contagionCrontroller;
        contagionCrontroller = new ContagionController();
        return contagionCrontroller;
    }
    public static NotifyContagionController getNotifyContagionController () {
        if (notifyContagionController != null)
            return notifyContagionController;
        notifyContagionController = new NotifyContagionController();
        return notifyContagionController;
    }
    public static MarkPositionInClassroomController getMarkPositionInClassroomController () {
        if (markPositionInClassroomController != null)
            return markPositionInClassroomController;
        markPositionInClassroomController = new MarkPositionInClassroomController();
        return markPositionInClassroomController;
    }
}
