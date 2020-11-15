package com.example.gescov.DomainLayer.Services.ResponseControllers;

import android.widget.Toast;

import com.example.gescov.DomainLayer.DomainControlFactory;
import com.example.gescov.DomainLayer.Services.IDeleteSchoolService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DeleteSchoolResponseController implements Callback<ResponseBody> {

    Retrofit retrofit;

    public DeleteSchoolResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        DomainControlFactory.getSchoolsModelCrontroller().refreshSchoolList();
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }

    public void deleteSchoolRequest(String schoolId, String userID) {
        IDeleteSchoolService deleteSchoolService = retrofit.create(IDeleteSchoolService.class);

        Call<ResponseBody> call = deleteSchoolService.deleteSchoolById(schoolId, userID);
        call.enqueue(this);
    }
}
