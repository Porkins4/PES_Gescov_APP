package com.example.gescov.DomainLayer.Services.Retrofit.Interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRequestAccessSchoolByCodeService {

    @PUT("api/schools/{id}")
    Call<ResponseBody> requestAccess(@Path("id") String schoolId,
                                     @Query("userID") String userId,
                                     @Query("code") String code);
}
