package com.example.gescov.DomainLayer.Services.Retrofit.ResponseControllers;


import com.example.gescov.DomainLayer.Services.Retrofit.Interfaces.IDeleteSchoolAdminService;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DeleteSchoolAdminResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public DeleteSchoolAdminResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DomainControlFactory.getSchoolsModelCrontroller().refreshCurrentSchool();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public void deleteSchoolAdmin(String currentSchoolId, String currentUserId, String adminId) {
        IDeleteSchoolAdminService service = retrofit.create(IDeleteSchoolAdminService.class);

        Call<String> call = service.deleteAdmin(currentSchoolId, currentUserId, adminId);
        call.enqueue(this);
    }
}
