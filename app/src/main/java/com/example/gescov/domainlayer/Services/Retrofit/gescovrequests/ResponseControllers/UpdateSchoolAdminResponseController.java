package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers;


import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces.IUpdateSchoolAdminService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateSchoolAdminResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public UpdateSchoolAdminResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
         if (response.code() == 200)
            DomainControlFactory.getSchoolsModelCrontroller().refreshCurrentSchool();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public void addNewAdmin(String currentSchoolId, String currentUserId, String newAdminID) {
        IUpdateSchoolAdminService service = retrofit.create(IUpdateSchoolAdminService.class);

        Call<String> call = service.addNewAdminToSchool(currentSchoolId, currentUserId, newAdminID);
        call.enqueue(this);
    }
}
