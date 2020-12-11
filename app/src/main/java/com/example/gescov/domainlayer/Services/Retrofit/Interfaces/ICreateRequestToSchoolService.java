package com.example.gescov.domainlayer.Services.Retrofit.Interfaces;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ICreateRequestToSchoolService {
    @POST("api/entryRequests")
    Call<String> createRequestToSchool(@Body RequestBody body);
}
