package com.example.gescov.domainlayer.Services.Retrofit.Interfaces;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IUpdateSchoolClassroomService {
    @PUT("api/classrooms/")
    Call<String> updateSchoolClassroom(@Query("id") String id,
                                       @Query("name") String name,
                                       @Query("numRows") int numRows,
                                       @Query("numCols") int numCols);
}
