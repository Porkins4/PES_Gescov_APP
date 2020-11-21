package com.example.gescov.ViewLayer.ViewLayerSingletons;

import com.example.gescov.ViewLayer.ContagionList.ContagionController;
import com.example.gescov.ViewLayer.LoginAndRegister.LoadingProfileController;
import com.example.gescov.ViewLayer.MarkPositionInClassroom.MarkPositionInClassroomController;
import com.example.gescov.ViewLayer.ClassroomActivities.SchoolClassroomList.SchoolClassroomsCrontroller;
import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolsCrontroller;
import com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassSession.StudentsInClassSessionController;
import com.example.gescov.ViewLayer.home.NotifyContagionController;
import com.example.gescov.ViewLayer.home.TracingTestController;

public class PresentationControlFactory {
    private static ViewLayerController viewLayerController;
    private static SchoolsCrontroller schoolsCrontroller;
    private static SchoolClassroomsCrontroller classroomsCrontroller;
    private static ContagionController contagionCrontroller;
    private static NotifyContagionController notifyContagionController;
    private static MarkPositionInClassroomController markPositionInClassroomController;
    private static StudentsInClassSessionController studentsInClassSessionController;
    private static TracingTestController tracingTestController;
    private static LoadingProfileController loadingProfileController;

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

    public static StudentsInClassSessionController getStudentsInClassSessionController () {
        if (studentsInClassSessionController != null)
            return studentsInClassSessionController;
        studentsInClassSessionController = new StudentsInClassSessionController();
        return studentsInClassSessionController;
    }

    public static TracingTestController getTracingTestControllerController() {
        if (tracingTestController != null)
            return tracingTestController;
        tracingTestController = new TracingTestController();
        return tracingTestController;
    }
  
    public static LoadingProfileController getLoadingProfileController() {
        if (loadingProfileController != null)
            return loadingProfileController;
        loadingProfileController = new LoadingProfileController();
        return loadingProfileController;
    }
}
