package com.example.gescov.viewlayer.Singletons;

import com.example.gescov.viewlayer.ContagionList.ContagionController;
import com.example.gescov.viewlayer.schoolrequests.SchoolRequestsController;
import com.example.gescov.viewlayer.SignUpAndLogin.LoadingProfileActivity;
import com.example.gescov.viewlayer.SignUpAndLogin.LoadingProfileController;
import com.example.gescov.viewlayer.ClassroomActivities.MarkPositionInClassroom.MarkPositionInClassroomController;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolClassroomsCrontroller;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.SchoolsCrontroller;
import com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession.StudentsInClassSessionController;
import com.example.gescov.viewlayer.UpdateUserProfile.UpdateUserProfileController;
import com.example.gescov.viewlayer.ViewLayerController;
import com.example.gescov.viewlayer.home.NotifyContagionController;
import com.example.gescov.viewlayer.home.TracingTestController;
import com.example.gescov.viewlayer.chatlist.ChatListController;

import androidx.lifecycle.ViewModelProvider;

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
    private static UpdateUserProfileController updateUserProfileController;
    private static LoadingProfileActivity loadingProfileActivity;
    private static ViewModelProvider viewModelProvider;
    private static SchoolRequestsController schoolRequestsController;
    private static ChatListController chatListController;

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

    public static UpdateUserProfileController getUpdateUserProfileController() {
        if (updateUserProfileController != null)
            return updateUserProfileController;
        updateUserProfileController = new UpdateUserProfileController();
        return updateUserProfileController;
    }

    public static ViewModelProvider getViewModelProvider() {
        return viewModelProvider;
    }

    public static void setViewModelProvider(ViewModelProvider provider) {
        viewModelProvider = provider;
    }

    public static ChatListController getChatListController() {
        if (chatListController != null) return chatListController;
        chatListController = new ChatListController();
        return chatListController;
    }

    public static SchoolRequestsController getSchoolRequestsController() {
        if (schoolRequestsController != null)
            return schoolRequestsController;
        schoolRequestsController = new SchoolRequestsController();
        return schoolRequestsController;
    }
}
