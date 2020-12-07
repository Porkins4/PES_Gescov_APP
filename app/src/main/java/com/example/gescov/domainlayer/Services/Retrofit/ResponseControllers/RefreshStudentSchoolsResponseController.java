package com.example.gescov.domainlayer.Services.Retrofit.ResponseControllers;


import com.example.gescov.domainlayer.Services.Retrofit.Interfaces.IRefreshStudentSchoolsService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RefreshStudentSchoolsResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public RefreshStudentSchoolsResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DomainControlFactory.getSchoolsModelCrontroller().refreshSchoolList(response.body().toString());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public void refreshSchools(String userId) {
        IRefreshStudentSchoolsService refreshStudentSchoolsService = retrofit.create(IRefreshStudentSchoolsService.class);

        Call<String> call = refreshStudentSchoolsService.refreshSchools(userId);
        call.enqueue(this);
    }
}
