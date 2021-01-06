package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers;


import com.example.gescov.domainlayer.Classmodels.WallEntry;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces.ICreateForumEntryReplyService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateForumEntryReplyResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public CreateForumEntryReplyResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
         if (response.code() == 200) {
             try {
                 WallEntry we = DomainControlFactory.getForumModelController().getWallEntryFromJSON(new JSONObject(response.body()));
                 DomainControlFactory.getForumModelController().refreshWallEntryReplies(we);
             } catch (JSONException e) {
                 e.printStackTrace();
             }
         }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
    }

    public void createForumEntryReply(String content, String wallEntryId, String creatorId) {
        ICreateForumEntryReplyService service = retrofit.create(ICreateForumEntryReplyService.class);
        JSONObject forumEntryReply = new JSONObject();

        try {
            forumEntryReply.put("text", content);
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), String.valueOf(forumEntryReply));
            Call<String> call = service.createForumEntryReply(wallEntryId, body, creatorId);
            call.enqueue(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
