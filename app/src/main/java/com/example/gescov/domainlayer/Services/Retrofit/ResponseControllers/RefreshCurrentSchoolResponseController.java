package com.example.gescov.domainlayer.Services.Retrofit.ResponseControllers;


import com.example.gescov.domainlayer.Services.Retrofit.Interfaces.IRefreshSchoolByIdService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RefreshCurrentSchoolResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public RefreshCurrentSchoolResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DomainControlFactory.getSchoolsModelCrontroller().refreshCurrentSchool(response.body().toString());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public void refreshSchool(String schoolID) {
        IRefreshSchoolByIdService service = retrofit.create(IRefreshSchoolByIdService.class);

        Call<String> call = service.refreshSchoolById(schoolID);
        call.enqueue(this);
    }
}
