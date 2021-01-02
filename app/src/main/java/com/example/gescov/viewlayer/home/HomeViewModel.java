package com.example.gescov.viewlayer.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Boolean> risk;
    private MutableLiveData<String> name;

    public HomeViewModel() {
        risk = new MutableLiveData<>();
        name = new MutableLiveData<>();
        risk.setValue(false);
    }

    public LiveData<Boolean> getRisk() {
        return risk;
    }

    public void setRisk (boolean risk) {
        this.risk.setValue(risk);
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public void setUserToken(String token) {
        PresentationControlFactory.getHomeController().setUserToken(token);
    }
}