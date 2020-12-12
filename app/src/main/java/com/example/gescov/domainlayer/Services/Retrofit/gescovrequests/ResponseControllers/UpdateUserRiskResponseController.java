package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers;

import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces.IUpdateUserRisk;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateUserRiskResponseController implements Callback<String> {
    private final Retrofit retrofit;

    public UpdateUserRiskResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DomainControlFactory.getUserModelController().refreshLoggedUser();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
    }

    public void updateRisk(String userId) {
        IUpdateUserRisk service = retrofit.create(IUpdateUserRisk.class);
        Call<String> call = service.updateUserRisk(userId);
        call.enqueue(this);
    }
}
