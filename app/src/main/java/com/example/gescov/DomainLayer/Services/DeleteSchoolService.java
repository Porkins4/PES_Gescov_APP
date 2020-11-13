package com.example.gescov.DomainLayer.Services;

import com.example.gescov.DomainLayer.Classmodels.School;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.HTTP;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DeleteSchoolService {

    @DELETE ("api/school/{id}")
    Call<ResponseBody> deleteSchoolById(@Path("id") String id, @Query("adminID") String userID);
}
