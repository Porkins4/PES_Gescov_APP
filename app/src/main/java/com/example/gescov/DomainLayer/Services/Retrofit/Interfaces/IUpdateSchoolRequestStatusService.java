package com.example.gescov.DomainLayer.Services.Retrofit.Interfaces;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IUpdateSchoolRequestStatusService {
    @PUT("api/entryRequests/requestID/{requestID}")
    Call<String> updateSchoolRequestStatus(@Path("requestID") String requestId,
                                           @Query("state") String status,
                                           @Query("adminID") String adminId);
}
