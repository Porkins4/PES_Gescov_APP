package com.example.gescov.domainlayer.Services.Retrofit.ResponseControllers;


import com.example.gescov.domainlayer.Services.Retrofit.Interfaces.IRefreshSchoolRequestsBySchoolIdService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RefreshSchoolRequestsBySchoolIdResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public RefreshSchoolRequestsBySchoolIdResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DomainControlFactory.getSchoolRequestModelController().setSchoolRequestsList(response.body().toString());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public void refreshSchoolClassroomsList(String schoolId) {
        IRefreshSchoolRequestsBySchoolIdService refreshSchoolRequestsBySchoolIdService = retrofit.create(IRefreshSchoolRequestsBySchoolIdService.class);

        Call<String> call = refreshSchoolRequestsBySchoolIdService.refreshSchoolRequests(schoolId);
        call.enqueue(this);
    }
}
