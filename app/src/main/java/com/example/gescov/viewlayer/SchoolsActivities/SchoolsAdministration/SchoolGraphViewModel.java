package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import java.util.List;

public class SchoolGraphViewModel extends ViewModel {
    private List<Pair<String,Integer>> contPerMonth;
    private MutableLiveData<Boolean> received;

    public void setResponseOfGraph(List<Pair<String, Integer>> contagionPerMonth) {
        this.contPerMonth = contagionPerMonth;
        received.setValue(true);
    }

    public LiveData<Boolean> getGraphInfo(String schoolID) {
        if (received == null) {
            received = new MutableLiveData<>();
            PresentationControlFactory.getSchoolsCrontroller().setSchoolGraphModel(this);
            PresentationControlFactory.getSchoolsCrontroller().setGraph(schoolID);
        }
        return received;
    }

    public List<Pair<String, Integer>> getContagionPerMonth() { return contPerMonth;}
}
