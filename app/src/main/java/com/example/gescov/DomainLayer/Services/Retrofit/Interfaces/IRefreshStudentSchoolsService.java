package com.example.gescov.DomainLayer.Services.Retrofit.Interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRefreshStudentSchoolsService {
    @GET("/api/users/{id}/schools")
    Call<String> refreshSchools(@Path("id") String userId);
}
