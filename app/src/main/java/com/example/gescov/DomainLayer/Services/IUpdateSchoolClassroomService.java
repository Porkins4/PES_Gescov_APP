package com.example.gescov.DomainLayer.Services;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IUpdateSchoolClassroomService {
    @PUT("api/classrooms/")
    Call<String> updateSchoolClassroom(@Query("id") String id,
                                       @Query("name") String name,
                                       @Query("capacity") int capacity,
                                       @Query("numRows") int numRows,
                                       @Query("numCols") int numCols);
}
