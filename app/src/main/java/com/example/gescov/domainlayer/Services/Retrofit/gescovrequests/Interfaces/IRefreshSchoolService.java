package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IRefreshSchoolService {
    @GET("api/schools/")
    Call<String> refreshSchoolList();
}
