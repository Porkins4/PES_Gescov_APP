package com.example.gescov.domainlayer.Services.mapsretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRefreshCoordinatesFromAddresService {
    @GET("forward")
    Call<String> refreshCoorinates(@Query("query") String schoolAddress,
                                   @Query("access_key") String key);
}
