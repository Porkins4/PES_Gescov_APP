package com.example.gescov.DomainLayer.Services.ResponseControllers;


import com.example.gescov.DomainLayer.DomainControlFactory;
import com.example.gescov.DomainLayer.Services.IDeleteSchoolService;
import com.example.gescov.DomainLayer.Services.IRefreshSchoolService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RefreshSchoolResponseController implements Callback<ResponseBody> {

    private final Retrofit retrofit;

    public RefreshSchoolResponseController (Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        DomainControlFactory.getSchoolsModelCrontroller().refreshSchoolList(response.toString());
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }

    public void refreshSchoolList(String userId) {
        IRefreshSchoolService refreshSchoolService = retrofit.create(IRefreshSchoolService.class);

        Call<ResponseBody> call = refreshSchoolService.refreshSchoolList();
        call.enqueue(this);
    }
}
