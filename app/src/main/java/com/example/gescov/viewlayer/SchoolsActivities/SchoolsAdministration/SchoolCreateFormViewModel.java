package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SchoolCreateFormViewModel extends ViewModel {

    private MutableLiveData<String> latitude;
    private MutableLiveData<String> longitude;

    public SchoolCreateFormViewModel() {
        latitude = new MutableLiveData<>();
        longitude = new MutableLiveData<>();
        latitude.setValue("0");
        longitude.setValue("0");
    }

    public LiveData<String> getLatitude() {
        return latitude;
    }
    public LiveData<String> getLongitude() {
        return longitude;
    }


    public void updateCoordinates(String latitude, String longitude) {
        this.latitude.setValue(latitude);
        this.longitude.setValue(longitude);
    }
}