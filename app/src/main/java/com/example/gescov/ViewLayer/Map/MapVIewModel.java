package com.example.gescov.ViewLayer.Map;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;

import java.util.List;

public class MapVIewModel extends ViewModel {
    private MutableLiveData<List<Pair<School,Integer>>> schools;


    public MapVIewModel () {
    }


    public LiveData<List<Pair<School,Integer>>> getSchools () {
        if (schools == null) {
            schools = new MutableLiveData<>();
            PresentationControlFactory.getMapController().setViewModelControler(this);
            PresentationControlFactory.getMapController().getNumContagionPerSchool();
        }
        return schools;
    }

    public void setResponse( List<Pair<School, Integer>> schools ) {
        this.schools.setValue(schools);

    }
}
