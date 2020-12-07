package com.example.gescov.domainlayer.Services.Retrofit.Interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRequestAccessSchoolByCodeService {

    @POST("api/schools/{id}/entry")
    Call<ResponseBody> requestAccess(@Path("id") String schoolId,
                                     @Query("userID") String userId,
                                     @Query("code") String code);
}
