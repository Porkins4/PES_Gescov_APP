package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers;

import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces.IDeleteWallEntryService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DeleteWallEntryResponseController implements Callback<String> {

    Retrofit retrofit;

    public DeleteWallEntryResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DomainControlFactory.getForumModelController().refreshLoggedUserSchoolsWallEntries();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public void deleteWallEntry(String wallEntryId, String userID) {
        IDeleteWallEntryService service = retrofit.create(IDeleteWallEntryService.class);

        Call<String> call = service.deleteWallEntry(wallEntryId);
        call.enqueue(this);
    }
}
