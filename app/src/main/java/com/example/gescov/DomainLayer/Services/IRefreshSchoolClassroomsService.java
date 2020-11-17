package com.example.gescov.DomainLayer.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRefreshSchoolClassroomsService {
    @GET("api/classroom/school/")
    Call<String> refreshSchoolClassroomsList(@Query("schoolName") String schoolName);
}
