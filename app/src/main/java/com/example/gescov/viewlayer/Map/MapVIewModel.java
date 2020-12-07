package com.example.gescov.viewlayer.Map;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class MapVIewModel extends ViewModel {
    private MutableLiveData<List<Pair<School,Integer>>> schools;


    public MapVIewModel () {
        //Empty Constructor
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
