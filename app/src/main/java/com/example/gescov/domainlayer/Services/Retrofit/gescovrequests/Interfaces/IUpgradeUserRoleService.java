package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IUpgradeUserRoleService {
    @PUT("api/users/{id}/{profile}")
    Call<String> upgradeUserRole(@Path ("id") String userId, @Path ("profile") String Profile);
}
