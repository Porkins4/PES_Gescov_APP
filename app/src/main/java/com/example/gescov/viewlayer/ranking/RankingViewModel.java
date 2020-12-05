package com.example.gescov.viewlayer.ranking;

import android.content.Context;
import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class RankingViewModel extends ViewModel {
    private RankingAdapter adapter;
    private List<Pair<School,Integer>>schools;
    private MutableLiveData<Boolean> received;

    public RankingAdapter getAdapter(Context c) {
        adapter = new RankingAdapter(c,schools);
        return adapter;
    }

    public LiveData<Boolean> getRanking() {
        if (received == null) {
            received = new MutableLiveData<>();
            PresentationControlFactory.getRankingController().setViewModel(this);
            PresentationControlFactory.getRankingController().getRanking();
        }
        return received;
    }

    public void setResponse (List<Pair<School, Integer>> schools ) {
        this.schools = schools;
        received.setValue(true);
    }

}
