package com.example.gescov.domainlayer.Services.Retrofit.Interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IDeleteSchoolService {

    @DELETE ("api/schools/{id}")
    Call<ResponseBody> deleteSchoolById(@Path("id") String id, @Query("adminID") String userID);
}
