package com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.ResponseControllers;

import com.example.gescov.domainlayer.Services.Retrofit.gescovrequests.Interfaces.IUpdateSchoolRequestStatusService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateSchoolRequestStatusResponseController implements Callback<String> {
    private final Retrofit retrofit;

    public UpdateSchoolRequestStatusResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DomainControlFactory.getSchoolRequestModelController().getSchoolRequestsBySchoolId(DomainControlFactory.getSchoolsModelCrontroller().getCurrentSchool().getId());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public void updateSchoolRequestStatus(String status, String schoolRequestId, String userId) {
        IUpdateSchoolRequestStatusService service = retrofit.create(IUpdateSchoolRequestStatusService.class);
        Call<String> call = service.updateSchoolRequestStatus(schoolRequestId, status, userId);
        call.enqueue(this);
    }
}
