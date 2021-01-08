package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ICreateForumEntryService {
    @POST("api/wallEntry")
    Call<String> createForumEntry(@Body RequestBody body, @Query ("creatorID") String creatorId);
}
