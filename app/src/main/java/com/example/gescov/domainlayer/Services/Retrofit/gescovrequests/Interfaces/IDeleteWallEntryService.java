package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface IDeleteWallEntryService {
    @DELETE ("api/wallEntry/{id}")
    Call<String> deleteWallEntry(@Path("id") String wallEntryId);
}
