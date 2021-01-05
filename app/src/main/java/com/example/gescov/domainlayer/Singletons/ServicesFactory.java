package com.example.gescov.domainlayer.Singletons;


import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.CreateForumEntryReplyResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.CreateForumEntryResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.CreateRequestToSchoolResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.DeleteSchoolAdminResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.DeleteSchoolClassroomResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.DeleteSchoolResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.RefreshCurrentSchoolResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.RefreshSchoolClassroomsResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.RefreshSchoolRequestsBySchoolIdResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.RefreshSchoolResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.RefreshStudentSchoolsResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.RefreshUsersBySchoolIdResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.RefreshWallEntriesBySchoolIdResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.RequestAccessSchoolByCodeResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.UpdateSchoolAdminResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.UpdateSchoolClassroomResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.UpdateSchoolRequestStatusResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers.UpdateUserRiskResponseController;
import com.example.gescov.domainlayer.Services.Retrofit.mapsretrofit.RefreshCoordinatesFromAddressResponseController;
import com.example.gescov.domainlayer.Services.Volley.Implementors.AssignmentServiceImplementor;
import com.example.gescov.domainlayer.Services.Volley.Implementors.ChatServiceImplementor;
import com.example.gescov.domainlayer.Services.Volley.Implementors.ClassroomServiceImplementor;
import com.example.gescov.domainlayer.Services.Volley.Implementors.ContagionServiceImplementor;
import com.example.gescov.domainlayer.Services.Volley.Implementors.EventServiceImplementor;
import com.example.gescov.domainlayer.Services.Volley.Implementors.SchoolServiceImplementor;
import com.example.gescov.domainlayer.Services.Volley.Implementors.SubjectsServiceImplementor;
import com.example.gescov.domainlayer.Services.Volley.Implementors.UserServiceImplementor;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IAssignmentService;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IChatService;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IClassroomService;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IContagionService;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IEventService;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.ISchoolService;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.ISubjectsService;
import com.example.gescov.domainlayer.Services.Volley.Interfaces.IUserService;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServicesFactory {

    private static String GESCOV_BASE_URL = "https://gescov.herokuapp.com/";
    private static Retrofit retrofit;
    private static Retrofit mapsRetrofit;

    //volley
    private static IContagionService contagionservice;
    private static ISchoolService schoolService;
    private static IUserService userService;
    private static IClassroomService classroomService;
    private static IAssignmentService assignmentService;
    private static IChatService chatService;
    private static ISubjectsService subjectsService;
    private static IEventService eventService;

    //retrofit
    private static DeleteSchoolResponseController deleteSchoolResponseController;
    private static RefreshSchoolResponseController refreshSchoolResponseController;
    private static RefreshSchoolClassroomsResponseController refreshSchoolClassroomsResponseController;
    private static UpdateSchoolClassroomResponseController updateSchoolClassroomsResponseController;
    private static DeleteSchoolClassroomResponseController deleteSchoolClassroomsResponseController;
    private static UpdateUserRiskResponseController updateUserRiskResponseController;
    private static RefreshSchoolRequestsBySchoolIdResponseController refreshSchoolRequestsBySchoolIdResponseController;
    private static UpdateSchoolRequestStatusResponseController updateSchoolRequestStatusResponseController;
    private static RefreshStudentSchoolsResponseController refreshStudentSchoolsResponseController;
    private static RefreshUsersBySchoolIdResponseController refreshUsersBySchoolIdResponseController;
    private static UpdateSchoolAdminResponseController updateSchoolAdminResponseController;
    private static DeleteSchoolAdminResponseController deleteSchoolAdminResponseController;
    private static RequestAccessSchoolByCodeResponseController requestAccessSchoolByCodeResponseController;
    private static RefreshCurrentSchoolResponseController refreshCurrentSchoolResponseController;

    //retrofit maps
    private static RefreshCoordinatesFromAddressResponseController refreshCoordinatesFromAddressResponseController;
    private static CreateRequestToSchoolResponseController createRequestToSchoolResponseController;
    private static CreateForumEntryResponseController createForumEntryResponseController;
    private static RefreshWallEntriesBySchoolIdResponseController refreshWallEntriesBySchoolIdResponseController;
    private static CreateForumEntryReplyResponseController createForumEntryReplyResponseController;

    private static Retrofit getRetrofit() {
        if (retrofit != null) return retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://gescov.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return retrofit;
    }

    private static Retrofit getMapsRetrofit() {
        if (mapsRetrofit != null) return mapsRetrofit;
        mapsRetrofit = new Retrofit.Builder()
                .baseUrl("http://api.positionstack.com/v1/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return mapsRetrofit;
    }

    public static IContagionService getContagionService() {
        if (contagionservice != null) return contagionservice;
        contagionservice = new ContagionServiceImplementor();
        return contagionservice;
    }

    public static ISchoolService getSchoolService() {
        if (schoolService != null) return schoolService;
        schoolService = new SchoolServiceImplementor();
        return schoolService;
    }

    public static DeleteSchoolResponseController getDeleteSchoolResponseController() {
        if (deleteSchoolResponseController != null) return deleteSchoolResponseController;
        deleteSchoolResponseController = new DeleteSchoolResponseController(getRetrofit());
        return deleteSchoolResponseController;
    }


    public static RefreshSchoolResponseController getRefreshSchoolResponseController() {
        if (refreshSchoolResponseController != null) return refreshSchoolResponseController;
        refreshSchoolResponseController = new RefreshSchoolResponseController(getRetrofit());
        return refreshSchoolResponseController;
    }

    public static RefreshSchoolClassroomsResponseController getRefreshSchoolClassroomsResponseController() {
        if (refreshSchoolClassroomsResponseController != null) return refreshSchoolClassroomsResponseController;
        refreshSchoolClassroomsResponseController = new RefreshSchoolClassroomsResponseController(getRetrofit());
        return refreshSchoolClassroomsResponseController;
    }

    public static UpdateSchoolClassroomResponseController getUpdateSchoolClassroomController() {
        if (updateSchoolClassroomsResponseController != null) return updateSchoolClassroomsResponseController;
        updateSchoolClassroomsResponseController = new UpdateSchoolClassroomResponseController(getRetrofit());
        return updateSchoolClassroomsResponseController;
    }

    public static DeleteSchoolClassroomResponseController getDeleteSchoolClassroomResponseController() {
        if (deleteSchoolClassroomsResponseController != null) return deleteSchoolClassroomsResponseController;
        deleteSchoolClassroomsResponseController = new DeleteSchoolClassroomResponseController(getRetrofit());
        return deleteSchoolClassroomsResponseController;
    }

    public static IUserService getUserService() {
        if (userService != null) return userService;
        userService = new UserServiceImplementor();
        return userService;
    }

    public static IClassroomService getClassroomService() {
        if (classroomService != null) return classroomService;
        classroomService = new ClassroomServiceImplementor();
        return classroomService;
    }

    public static UpdateUserRiskResponseController getUpdateUserRiskResponseController() {
        if (updateUserRiskResponseController != null) return updateUserRiskResponseController;
        updateUserRiskResponseController = new UpdateUserRiskResponseController(getRetrofit());
        return updateUserRiskResponseController;
    }

    public static IAssignmentService getAssignmentService() {
        if (assignmentService != null) return assignmentService;
        assignmentService = new AssignmentServiceImplementor();
        return assignmentService;
    }

    public static RefreshSchoolRequestsBySchoolIdResponseController getRefreshSchoolRequestsBySchoolIdResponseController() {
        if (refreshSchoolRequestsBySchoolIdResponseController != null) return refreshSchoolRequestsBySchoolIdResponseController;
        refreshSchoolRequestsBySchoolIdResponseController = new RefreshSchoolRequestsBySchoolIdResponseController(getRetrofit());
        return refreshSchoolRequestsBySchoolIdResponseController;
    }

    public static UpdateSchoolRequestStatusResponseController getUpdateSchoolRequestStatusResponseController() {
        if (updateSchoolRequestStatusResponseController != null) return updateSchoolRequestStatusResponseController;
        updateSchoolRequestStatusResponseController = new UpdateSchoolRequestStatusResponseController(getRetrofit());
        return updateSchoolRequestStatusResponseController;
    }

    public static RefreshStudentSchoolsResponseController getRefreshStudentSchoolsResponseController() {
        if (refreshStudentSchoolsResponseController != null) return refreshStudentSchoolsResponseController;
        refreshStudentSchoolsResponseController = new RefreshStudentSchoolsResponseController(getRetrofit());
        return refreshStudentSchoolsResponseController;
    }

    public static RefreshUsersBySchoolIdResponseController getRefreshUsersBySchoolIdResponseController() {
        if (refreshUsersBySchoolIdResponseController != null) return refreshUsersBySchoolIdResponseController;
        refreshUsersBySchoolIdResponseController = new RefreshUsersBySchoolIdResponseController(getRetrofit());
        return refreshUsersBySchoolIdResponseController;
    }

    public static UpdateSchoolAdminResponseController getUpdateSchoolAdminResponseController() {
        if (updateSchoolAdminResponseController != null) return updateSchoolAdminResponseController;
        updateSchoolAdminResponseController = new UpdateSchoolAdminResponseController(getRetrofit());
        return updateSchoolAdminResponseController;
    }

    public static DeleteSchoolAdminResponseController getDeleteSchoolAdminResponseController() {
        if (deleteSchoolAdminResponseController != null) return deleteSchoolAdminResponseController;
        deleteSchoolAdminResponseController = new DeleteSchoolAdminResponseController(getRetrofit());
        return deleteSchoolAdminResponseController;
    }

    public static IChatService getChatService() {
        if (chatService != null) return chatService;
        chatService = new ChatServiceImplementor();
        return chatService;
    }

    public static RequestAccessSchoolByCodeResponseController getRequestAccessSchoolByCodeResponseController() {
        if (requestAccessSchoolByCodeResponseController != null) return requestAccessSchoolByCodeResponseController;
        requestAccessSchoolByCodeResponseController = new RequestAccessSchoolByCodeResponseController(getRetrofit());
        return requestAccessSchoolByCodeResponseController;
    }

    public static RefreshCurrentSchoolResponseController getRefreshCurrentSchoolResponseController() {
        if (refreshCurrentSchoolResponseController != null) return refreshCurrentSchoolResponseController;
        refreshCurrentSchoolResponseController = new RefreshCurrentSchoolResponseController(getRetrofit());
        return refreshCurrentSchoolResponseController;
    }

    public static RefreshCoordinatesFromAddressResponseController getRefreshCoordinatesFromAddressResponseController() {
        if (refreshCoordinatesFromAddressResponseController != null) return refreshCoordinatesFromAddressResponseController;
        refreshCoordinatesFromAddressResponseController = new RefreshCoordinatesFromAddressResponseController(getMapsRetrofit());
        return refreshCoordinatesFromAddressResponseController;
    }

    public static CreateRequestToSchoolResponseController getCreateRequestToSchoolResponseController () {
        if (createRequestToSchoolResponseController != null) return createRequestToSchoolResponseController;
        createRequestToSchoolResponseController = new CreateRequestToSchoolResponseController(getRetrofit());
        return createRequestToSchoolResponseController;
    }

    public static ISubjectsService getSubjectsService() {
        if (subjectsService != null) return subjectsService;
        subjectsService = new SubjectsServiceImplementor();
        return subjectsService;
    }


    public static IEventService getEventService() {
        if (eventService != null) return eventService;
        eventService = new EventServiceImplementor();
        return eventService;
    }

    public static CreateForumEntryResponseController getCreateForumEntryResponseController() {
        if (createForumEntryResponseController != null) return createForumEntryResponseController;
        createForumEntryResponseController = new CreateForumEntryResponseController(getRetrofit());
        return createForumEntryResponseController;
    }

    public static RefreshWallEntriesBySchoolIdResponseController getRefreshWallEntriesBySchoolIdResponseController() {
        if (refreshWallEntriesBySchoolIdResponseController != null) return refreshWallEntriesBySchoolIdResponseController;
        refreshWallEntriesBySchoolIdResponseController = new RefreshWallEntriesBySchoolIdResponseController(getRetrofit());
        return refreshWallEntriesBySchoolIdResponseController;

    }

    public static CreateForumEntryReplyResponseController getCreateForumEntryReplyResponseController() {
        if (createForumEntryReplyResponseController != null) return createForumEntryReplyResponseController;
        createForumEntryReplyResponseController = new CreateForumEntryReplyResponseController(getRetrofit());
        return createForumEntryReplyResponseController;
    }
}
