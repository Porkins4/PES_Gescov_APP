package com.example.gescov.DomainLayer.Services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IDeleteSchoolClassroomService {

    @DELETE ("api/classrooms/{id}")
    Call<ResponseBody> deleteSchoolClassroomById(@Path("id") String id, @Query("adminID") String userID);
}
