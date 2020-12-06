package com.example.gescov.domainlayer.Services.Retrofit.Interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRefreshSchoolRequestsBySchoolIdService {
    @GET("api/entryRequests/schoolID/{schoolID}")
    Call<String> refreshSchoolRequests(@Path("schoolID") String schoolID);
}
