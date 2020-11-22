package com.example.gescov.DomainLayer.Services.ResponseControllers;

import com.example.gescov.DomainLayer.DomainControlFactory;
import com.example.gescov.DomainLayer.Services.IUpdateSchoolClassroomService;
import com.example.gescov.DomainLayer.Services.IUpdateUserRisk;

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
        DomainControlFactory.getUserController().getTypeProfile();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
int i = 3;
    }

    public void updateRisk(String userId) {
        IUpdateUserRisk service = retrofit.create(IUpdateUserRisk.class);
        Call<String> call = service.updateUserRisk(userId);
        call.enqueue(this);
    }
}