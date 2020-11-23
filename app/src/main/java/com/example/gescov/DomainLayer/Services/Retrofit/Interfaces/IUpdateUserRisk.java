package com.example.gescov.DomainLayer.Services.Retrofit.Interfaces;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IUpdateUserRisk {
    @PUT("api/users/{id}")
    Call<String> updateUserRisk(@Path("id") String id);
}
