package com.example.gescov.DomainLayer.Services.Retrofit.ResponseControllers;


import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;
import com.example.gescov.DomainLayer.Services.Retrofit.Interfaces.IRefreshSchoolService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RefreshSchoolResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public RefreshSchoolResponseController (Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DomainControlFactory.getSchoolsModelCrontroller().refreshSchoolList(response.body().toString());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public void refreshSchoolList(String userId) {
        IRefreshSchoolService refreshSchoolService = retrofit.create(IRefreshSchoolService.class);

        Call<String> call = refreshSchoolService.refreshSchoolList();
        call.enqueue(this);
    }
}
