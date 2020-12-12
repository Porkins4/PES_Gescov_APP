package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers;


import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces.IRefreshUsersBySchoolIdService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RefreshUsersBySchoolIdResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public RefreshUsersBySchoolIdResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DomainControlFactory.getUserModelController().setUsersList(response.body().toString());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public void refreshUsersBySchoolId(String schoolId) {
        IRefreshUsersBySchoolIdService refreshSchoolRequestsBySchoolIdService = retrofit.create(IRefreshUsersBySchoolIdService.class);

        Call<String> call = refreshSchoolRequestsBySchoolIdService.refreshSchoolRequests(schoolId);
        call.enqueue(this);
    }
}
