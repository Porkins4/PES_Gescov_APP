package com.example.gescov.domainlayer.Services.Retrofit.Interfaces;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IUpdateSchoolAdminService {
    @PUT("api/schools/{id}/updateAdmin")
    Call<String> addNewAdminToSchool(@Path("id") String schoolId,
                                       @Query("admin") String adminId,
                                       @Query("newAdmin") String newAdminId);
}
