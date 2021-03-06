package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IUpdateUserRisk {
    @PUT("api/users/{id}")
    Call<String> updateUserRisk(@Path("id") String id);
}
