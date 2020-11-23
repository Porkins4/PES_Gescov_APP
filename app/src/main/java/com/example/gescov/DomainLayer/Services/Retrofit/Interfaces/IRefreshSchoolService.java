package com.example.gescov.DomainLayer.Services.Retrofit.Interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRefreshSchoolService {
    @GET("api/schools/")
    Call<String> refreshSchoolList();
}
