package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers;


import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces.IRefreshSchoolClassroomsService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RefreshSchoolClassroomsResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public RefreshSchoolClassroomsResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DomainControlFactory.getClassroomModelController().setClassroomList(response.body().toString());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public void refreshSchoolClassroomsList(String schoolName) {
        IRefreshSchoolClassroomsService refreshSchoolClassroomsService = retrofit.create(IRefreshSchoolClassroomsService.class);

        Call<String> call = refreshSchoolClassroomsService.refreshSchoolClassroomsList(schoolName);
        call.enqueue(this);
    }
}
