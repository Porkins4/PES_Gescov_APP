package com.example.gescov.domainlayer.Services.mapsretrofit;


import com.example.gescov.domainlayer.Singletons.DomainControlFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RefreshCoordinatesFromAddressResponseController implements Callback<String> {

    private final Retrofit retrofit;

    public RefreshCoordinatesFromAddressResponseController(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        String s = response.body().toString();
        try {
            JSONObject resp = new JSONObject(response.body());
            JSONArray data = resp.getJSONArray("data");
            if (data.length() > 0) {
                JSONObject firstEntry = new JSONObject(data.get(0).toString());
                String latitude = firstEntry.getString("latitude");
                String longitude = firstEntry.getString("longitude");
                DomainControlFactory.getSchoolsModelCrontroller().updateCoordinatesSchoolCreationForm(latitude, longitude);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
    }

    public void refreshCoordinates(String schoolAddress) {
        IRefreshCoordinatesFromAddresService service = retrofit.create(IRefreshCoordinatesFromAddresService.class);

        Call<String> call = service.refreshCoorinates(schoolAddress, "208f17bf1db6d4723d2e4205c7622c50");
        call.enqueue(this);
    }
}
