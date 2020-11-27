package com.example.gescov.DomainLayer.Singletons;

import com.example.gescov.DomainLayer.Services.Retrofit.ResponseControllers.DeleteSchoolClassroomResponseController;
import com.example.gescov.DomainLayer.Services.Retrofit.ResponseControllers.DeleteSchoolResponseController;
import com.example.gescov.DomainLayer.Services.Retrofit.ResponseControllers.RefreshSchoolClassroomsResponseController;
import com.example.gescov.DomainLayer.Services.Retrofit.ResponseControllers.RefreshSchoolRequestsBySchoolIdResponseController;
import com.example.gescov.DomainLayer.Services.Retrofit.ResponseControllers.RefreshSchoolResponseController;
import com.example.gescov.DomainLayer.Services.Retrofit.ResponseControllers.UpdateSchoolClassroomResponseController;
import com.example.gescov.DomainLayer.Services.Retrofit.ResponseControllers.UpdateUserRiskResponseController;
import com.example.gescov.DomainLayer.Services.Volley.Implementors.AssignmentServiceImplementor;
import com.example.gescov.DomainLayer.Services.Volley.Implementors.ClassroomServiceImplementor;
import com.example.gescov.DomainLayer.Services.Volley.Implementors.ContagionServiceImplementor;
import com.example.gescov.DomainLayer.Services.Volley.Implementors.SchoolServiceImplementor;
import com.example.gescov.DomainLayer.Services.Volley.Implementors.UserServiceImplementor;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.IAssignmentService;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.IClassroomService;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.IContagionService;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.ISchoolService;
import com.example.gescov.DomainLayer.Services.Volley.Interfaces.IUserService;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServicesFactory {

    private static String GESCOV_BASE_URL = "https://gescov.herokuapp.com/";
    private static Retrofit retrofit;

    //volley
    private static IContagionService contagionservice;
    private static ISchoolService schoolService;
    private static IUserService userService;
    private static IClassroomService classroomService;
    private static IAssignmentService assignmentService;

    //retrofit
    private static DeleteSchoolResponseController deleteSchoolResponseController;
    private static RefreshSchoolResponseController refreshSchoolResponseController;
    private static RefreshSchoolClassroomsResponseController refreshSchoolClassroomsResponseController;
    private static UpdateSchoolClassroomResponseController updateSchoolClassroomsResponseController;
    private static DeleteSchoolClassroomResponseController deleteSchoolClassroomsResponseController;
    private static UpdateUserRiskResponseController updateUserRiskResponseController;
    private static RefreshSchoolRequestsBySchoolIdResponseController refreshSchoolRequestsBySchoolIdResponseController;

    private static Retrofit getRetrofit() {
        if (retrofit != null) return retrofit;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gescov.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return retrofit;
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
}
