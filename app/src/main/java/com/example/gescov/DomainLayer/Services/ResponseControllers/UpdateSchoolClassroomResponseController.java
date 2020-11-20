package com.example.gescov.DomainLayer.Services.ResponseControllers;

import com.example.gescov.DomainLayer.DomainControlFactory;
import com.example.gescov.DomainLayer.Services.IUpdateSchoolClassroomService;

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
        DomainControlFactory.getUserController().refreshSchoolClassrooms(DomainControlFactory.getSchoolsModelCrontroller().getCurrentSchool().getName());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public void updateSchoolClassroom(String id, String name, int rows, int cols, int capacity) {
        IUpdateSchoolClassroomService service = retrofit.create(IUpdateSchoolClassroomService.class);
        Call<String> call = service.updateSchoolClassroom(id, name, capacity, rows, cols);
        call.enqueue(this);
    }
}
