package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers;


import com.example.gescov.R;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces.IUpgradeUserRoleService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpgradeUserRoleResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public UpgradeUserRoleResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
         if (response.code() == 200) {
             DomainControlFactory.getUserModelController().refreshLoggedUser();
             DomainControlFactory.getModelController().toastMessage(R.string.user_promoted);
         } else {
             DomainControlFactory.getModelController().toastMessage(R.string.user_not_promoted);

         }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
    }

    public void upgradeUserRole(String userId, String role) {
        IUpgradeUserRoleService service = retrofit.create(IUpgradeUserRoleService.class);
        JSONObject forumEntryReply = new JSONObject();

        Call<String> call = service.upgradeUserRole(userId, role);
        call.enqueue(this);
    }
}
