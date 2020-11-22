package com.example.gescov.DomainLayer.Services;

import com.example.gescov.DomainLayer.Services.ResponseControllers.DeleteSchoolClassroomResponseController;
import com.example.gescov.DomainLayer.Services.ResponseControllers.DeleteSchoolResponseController;
import com.example.gescov.DomainLayer.Services.ResponseControllers.RefreshSchoolClassroomsResponseController;
import com.example.gescov.DomainLayer.Services.ResponseControllers.RefreshSchoolResponseController;
import com.example.gescov.DomainLayer.Services.ResponseControllers.UpdateSchoolClassroomResponseController;
import com.example.gescov.DomainLayer.Services.ResponseControllers.UpdateUserRiskResponseController;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServicesFactory {

    private static String GESCOV_BASE_URL = "https://gescov.herokuapp.com/";
    private static Retrofit retrofit;

    private static IContagionService contagionservice;
    private static ISchoolService schoolService;
    private static IUserService userService;
    private static IClassroomService classroomService;
    private static DeleteSchoolResponseController deleteSchoolResponseController;
    private static RefreshSchoolResponseController refreshSchoolResponseController;
    private static RefreshSchoolClassroomsResponseController refreshSchoolClassroomsResponseController;
    private static UpdateSchoolClassroomResponseController updateSchoolClassroomsResponseController;
    private static DeleteSchoolClassroomResponseController deleteSchoolClassroomsResponseController;
    private static UpdateUserRiskResponseController updateUserRiskResponseController;

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
      
    public static UpdateUserRiskResponseController getUpdateUserRiskResponseController() {
        if (updateUserRiskResponseController != null) return updateUserRiskResponseController;
        updateUserRiskResponseController = new UpdateUserRiskResponseController(getRetrofit());
        return updateUserRiskResponseController;
    }
}
