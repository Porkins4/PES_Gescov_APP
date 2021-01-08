package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRefreshWallEntriesBySchoolIdService {
    @GET("/api/wallEntry/{id}")
    Call<String> refreshSchoolById(@Path("id") String schoolId);
}
