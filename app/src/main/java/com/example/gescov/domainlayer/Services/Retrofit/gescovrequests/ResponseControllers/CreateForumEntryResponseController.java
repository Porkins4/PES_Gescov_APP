package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers;


import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces.ICreateForumEntryService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateForumEntryResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public CreateForumEntryResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
         if (response.code() == 200)
            DomainControlFactory.getForumModelController().refreshLoggedUserSchoolsWallEntries();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
    }

    public void createForumEntry(String titleEntry, String textEntry, String schoolId, String creatorId) {
        ICreateForumEntryService service = retrofit.create(ICreateForumEntryService.class);
        JSONObject forumEntry = new JSONObject();

        try {
            forumEntry.put("title", titleEntry);
            forumEntry.put("text", textEntry);
            forumEntry.put("schoolID", schoolId);
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), String.valueOf(forumEntry));
            Call<String> call = service.createForumEntry(body, creatorId);
            call.enqueue(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
