package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ICreateForumEntryReplyService {
    @POST("api/wallEntry/{id}/reply")
    Call<String> createForumEntryReply(@Path ("id") String wallEntryId, @Body RequestBody body, @Query ("userID") String creatorId);
}
