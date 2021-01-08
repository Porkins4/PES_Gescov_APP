package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRequestAccessSchoolByCodeService {

    @POST("/api/schools/entry")
    Call<String> requestAccess(@Query("schoolID") String schoolId,
                                     @Query("userID") String userId,
                                     @Query("code") String code);
}
