package com.example.gescov.DomainLayer.Services.Retrofit.ResponseControllers;

import com.example.gescov.DomainLayer.Services.Retrofit.Interfaces.IUpdateSchoolClassroomService;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateSchoolClassroomResponseController implements Callback<String> {
    private final Retrofit retrofit;

    public UpdateSchoolClassroomResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DomainControlFactory.getUserModelController().refreshSchoolClassrooms(DomainControlFactory.getSchoolsModelCrontroller().getCurrentSchool().getName());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public void updateSchoolClassroom(String id, String name, int rows, int cols) {
        IUpdateSchoolClassroomService service = retrofit.create(IUpdateSchoolClassroomService.class);
        Call<String> call = service.updateSchoolClassroom(id, name, rows, cols);
        call.enqueue(this);
    }
}
