package com.example.gescov.domainlayer.Services.Retrofit.Interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRefreshSchoolByIdService {
    @GET("api/schools/id/{id}")
    Call<String> refreshSchoolById(@Path("id") String schoolId);
}
