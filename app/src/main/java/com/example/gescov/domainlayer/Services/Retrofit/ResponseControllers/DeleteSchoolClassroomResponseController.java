package com.example.gescov.domainlayer.Services.Retrofit.ResponseControllers;

import com.example.gescov.domainlayer.Services.Retrofit.Interfaces.IDeleteSchoolClassroomService;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DeleteSchoolClassroomResponseController implements Callback<ResponseBody> {

    Retrofit retrofit;

    public DeleteSchoolClassroomResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        DomainControlFactory.getUserModelController().refreshSchoolClassrooms(DomainControlFactory.getSchoolsModelCrontroller().getCurrentSchool().getName());
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }

    public void deleteSchoolClassroomRequest(String schoolId, String userID) {
        IDeleteSchoolClassroomService deleteSchoolClassroomService = retrofit.create(IDeleteSchoolClassroomService.class);

        Call<ResponseBody> call = deleteSchoolClassroomService.deleteSchoolClassroomById(schoolId, userID);
        call.enqueue(this);
    }
}
