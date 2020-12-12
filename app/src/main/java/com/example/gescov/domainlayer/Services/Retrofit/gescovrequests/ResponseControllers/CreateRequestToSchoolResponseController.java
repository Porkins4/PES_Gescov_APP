package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers;


import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces.ICreateRequestToSchoolService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateRequestToSchoolResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public CreateRequestToSchoolResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
         if (response.code() == 200)
            DomainControlFactory.getSchoolsModelCrontroller().refreshSchoolList();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
    }

    public void createRequestToSchool(String userId, String schoolId) {
        ICreateRequestToSchoolService service = retrofit.create(ICreateRequestToSchoolService.class);
        JSONObject schoolRequest = new JSONObject();

        try {
            schoolRequest.put("userID", userId);
            schoolRequest.put("schoolID", schoolId);
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), String.valueOf(schoolRequest));
            Call<String> call = service.createRequestToSchool(body);
            call.enqueue(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
