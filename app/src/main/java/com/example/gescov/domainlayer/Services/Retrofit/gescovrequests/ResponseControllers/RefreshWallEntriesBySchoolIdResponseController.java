package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers;


import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces.IRefreshWallEntriesBySchoolIdService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RefreshWallEntriesBySchoolIdResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public RefreshWallEntriesBySchoolIdResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DomainControlFactory.getForumModelController().addSchoolWallEntries(response.body().toString());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public void refreshSchoolWallEntries(String schoolID) {
        IRefreshWallEntriesBySchoolIdService service = retrofit.create(IRefreshWallEntriesBySchoolIdService.class);

        Call<String> call = service.refreshSchoolById(schoolID);
        call.enqueue(this);
    }
}
