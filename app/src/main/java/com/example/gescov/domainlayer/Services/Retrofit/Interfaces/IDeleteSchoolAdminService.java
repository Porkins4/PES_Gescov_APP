package com.example.gescov.domainlayer.Services.Retrofit.Interfaces;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IDeleteSchoolAdminService {
    @DELETE ("api/schools/{id}/admin")
    Call<String> deleteAdmin(@Path("id") String schoolId,
                             @Query("creatorID") String adminId,
                             @Query("adminID") String newAdminId);
}
