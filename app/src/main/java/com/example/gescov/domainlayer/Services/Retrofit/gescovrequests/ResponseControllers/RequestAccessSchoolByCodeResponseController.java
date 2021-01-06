package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces.IRequestAccessSchoolByCodeService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RequestAccessSchoolByCodeResponseController implements Callback<ResponseBody> {

    Retrofit retrofit;

    public RequestAccessSchoolByCodeResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.code() == 200) {
            DomainControlFactory.getSchoolsModelCrontroller().refreshCurrentSchool();
            DomainControlFactory.getUserModelController().refreshLoggedUser();
            DomainControlFactory.getUserModelController().refreshSchoolList();
        } else {
            DomainControlFactory.getModelController().toastMessage(R.string.cannot_access_by_code);
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }

    public void requestAccess(String schoolId, String userID, String code) {
        IRequestAccessSchoolByCodeService deleteSchoolService = retrofit.create(IRequestAccessSchoolByCodeService.class);

        Call<ResponseBody> call = deleteSchoolService.requestAccess(schoolId, userID, code);
        call.enqueue(this);
    }
}
