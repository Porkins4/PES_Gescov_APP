package com.example.gescov.domainlayer.Services.Retrofit.Interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRefreshUsersBySchoolIdService {
    @GET("api/users/school")
    Call<String> refreshSchoolRequests(@Query("schoolID") String schoolID);
}
